package com.harma.tf.iclego;

import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.harma.tf.utils.Util;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Locator.ScreenshotOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.Clip;

public abstract class BaseApp {
	
	// *****************************************************************
	// Properties
	// *****************************************************************
	
	private String appName;
	private String highlightElementId;
	private String selector;
	private Locator appLocator;
	private List<ElementHandle> appElements;
	private ElementHandle appElement;
	protected Page page;
	protected ExtentTest test;
	private boolean highlightEnabled = false;
	
	// *****************************************************************
	// Constructor(s)
	// *****************************************************************
	
	public BaseApp(Page page, ExtentTest test, String appName) {
		
		this.page = page;
		this.test = test;
		this.appName = appName;
		this.highlightElementId = createHighlightElementId();
		this.selector = "div[data-app='" + appName + "']";
		this.appElement = page.querySelector(selector);
		this.appElements = page.querySelectorAll(selector);
		this.appLocator = page.locator(selector);
		
		/*
		if (this.appLocator.all().size() > 1) {
			test.log(Status.INFO, String.format("** Multiple Apps found '%d', will try to select the visible one", this.appLocator.all().size()));
			this.appLocator = getSingleVisibleApp();
		}
		*/
		//this.appLocator = getSingleVisibleApp();
		
		if (appElements.size() > 1) {
			test.log(Status.INFO, String.format("** Multiple Apps found '%d', will try to select the visible one", this.appLocator.all().size()));
			//this.appElement = getSingleVisibleApp();
		}
		this.appElement = getSingleVisibleApp();
	}
	
	// *****************************************************************
	// Methods
	// *****************************************************************
	
	public ElementHandle getAppElement() {
		return appElement;
	}
	
	/**
	 * Wrapper around playwright screenshot() capability
	 */
	public void takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + appName + ".png";
		// Getting the boundingBox of element which will be used in taking the screenshot
		BoundingBox boundingBox = appElement.boundingBox();
		// Using Page.screenshot() to take a screenshot of a specific web element
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setClip(boundingBox.x, boundingBox.y, boundingBox.width, boundingBox.height));
	}
	
	
	
	
	public void scrollIntoViewIfNeeded() {
		appElement.scrollIntoViewIfNeeded();
	}
	
	public void highlightApp() {
		
		if (!highlightEnabled) {
			return;
		}
		
		// .
		// ..
		// ...
		// We are going to inject a div in an absolutely positioned container on top page
		// Next for each appElement, we will inject a div at same position and dimensions but with
		// a z-index positioning it on top of everything. This div will have borders to indicate which
		// app element is being worked on. This is in contrast to simply giving the actual app element
		// a border which may cause a breakage in layout. We may also conditionally (with other methods)
		// show messages in the inserted div to indicate commands being executes, status, etc
		// .
		// ..
		// ...
		
		// First, conditionally add a local css file to the page containing styles for injected elements
		String _path = "D:/Users/Harma Davtian/eclipse-workspace2/PlaywrightPOMSeries/src/main/resources/css/ictf.css"; 
		Util.loadLocalCssFileIfNecessary(page, _path);
		
		// Conditionally inject the two container divs on the page
		// The first will be positioned absolutely with a high z-index to sit on top of everything
		// The second will be positioned relatively
		// We will inject other divs in the inner div later and position them accordingly
		
		StringBuilder _script = new StringBuilder();
		_script.append("(() => { if (document.querySelector('#ictf-highlighted-apps-main-container') != null){ return;}")
			.append("const body = document.body;")
			.append("const mainContainerDiv = document.createElement('div');")
			.append("mainContainerDiv.setAttribute('id', 'ictf-highlighted-apps-main-container');")
			.append("const innerDiv = document.createElement('div');")
			.append("innerDiv.setAttribute('id', 'ictf-highlighted-apps-inner-container');")
			.append("mainContainerDiv.appendChild(innerDiv);")
			.append("body.appendChild(mainContainerDiv);")
			.append("console.log('ictf container and inner divs injected for highlighting apps during test');")
			.append("})");
		
		page.evaluate(_script.toString());
		
		// get the scroll position
		LinkedHashMap<String, Object> scrollPosition = (LinkedHashMap<String, Object>) page.evaluate("() => {" +
                "return {" +
                "    x: window.scrollX," +
                "    y: window.scrollY" +
                "};" +
                "}");
		
		int scrollX = Integer.parseInt(scrollPosition.get("x").toString());
        int scrollY = Integer.parseInt(scrollPosition.get("y").toString());
        
        BoundingBox bbox = Util.getAppBoundingBox(this.appElement);
        int _top = (int)bbox.y + scrollY;
        int _left = (int)bbox.x + scrollX;
        		
		
		// insert a new div in the container div with boundingbox location and dimensions and add border
		ElementHandle containerDiv = page.querySelector("#ictf-highlighted-apps-inner-container");
		containerDiv.evaluate(
					"el => {" +
						"const appDiv = document.createElement('div');" +
						"appDiv.setAttribute('id', '"+ highlightElementId + "');" +
						"appDiv.setAttribute('class', 'ictf-injected-app-highlight-card ictf-visible ictf-border-3-green-dashed');" +
						"appDiv.style.top='"	+ _top 	+ "px';" +
						"appDiv.style.left='"	+ _left + "px';" +
						"appDiv.style.width='"+ (int)bbox.width + "px';" +
						"appDiv.style.height='"+ (int)bbox.height + "px';" +
						"el.appendChild(appDiv);" +
				"}");
		
		//page.evaluate("el => el.classList.add('ictf-highlight-element')", this.appElement);
		test.log(Status.INFO, "Added 'ictf-highlight-element' class to element: '" + appName + "'");
	}
	
	public boolean isVisible() {
		Boolean isVisible = appElement.isVisible();
		test.log(Status.INFO, "App '" + appName + "' is visible: " + Boolean.toString(isVisible));
		return isVisible;
	}
	
	public boolean singleInstanceExists() {
		//int count = appLocator.count();
		int count = appElements.size();
		boolean onlyOneFound = (count == 1) ? true : false;
		test.log(Status.INFO, "App '" + appName + "' exists as a single web element: " + Boolean.toString(onlyOneFound) + ", element count: " + Integer.toString(count));
		return onlyOneFound;
	}
	
	public boolean containsText_ignoringCaseAndSpecialChars(String expectedText) {
		List<String> allText = appLocator.allTextContents();
		boolean matchFound = false;
		for (String actualText : allText) {
			String _actualText = Util.removeSpacesAndSpecialCharacters(actualText);
			String _expectedText = Util.removeSpacesAndSpecialCharacters(expectedText);
			if (_actualText.indexOf(_expectedText) != -1) {
				matchFound = true;
				break;
			}
		}
		
		if (matchFound) {
			test.log(Status.INFO, "A Match for '"+ expectedText +"' was found");
		} else {
			test.log(Status.INFO, "A Match for '"+ expectedText +"' was not found");
		}
		
		return matchFound;
	}
	
	public boolean containsText_ignoringCaseAndSpecialChars2(String expectedText) {
		
		boolean matchFound = false;
		String allText = appElement.textContent();
		String _actualText = Util.removeSpacesAndSpecialCharacters(allText).toLowerCase();
		String _expectedText = Util.removeSpacesAndSpecialCharacters(expectedText).toLowerCase();
		if (_actualText.indexOf(_expectedText) != -1) {
			matchFound = true;
		}
		
		if (matchFound) {
			test.log(Status.INFO, "A Match for '"+ expectedText +"' was found");
		} else {
			test.log(Status.INFO, "A Match for '"+ expectedText +"' was not found");
		}
		
		return matchFound;
	}
	
	private ElementHandle getSingleVisibleApp() {
		
		// I am injecting a local custom js file which includes some functions that 
		// will be used in determining our complex methods of determining if apps are visible
		// on the page. We cannot rely on basic visibility checks
		Util.loadJsFile(page, "../PlaywrightPOMSeries/src/main/resources/js/util.js", "ictf");
		
		int visibleAppCount = getNumberOfVisibleAppsByThisName();
		
		if (visibleAppCount > 1) {
			test.log(Status.INFO, String.format("Strange sitution, multiple apps visible (%d) apps by name '%s' have been found", visibleAppCount, appName ));
		}
		
		ElementHandle theApp = null;
		int appIndex = 1;
		for (ElementHandle _appEl : appElements) {
			if ((boolean)page.evaluate("isElementVisible", _appEl)) {
				theApp = _appEl;
				test.log(Status.INFO, String.format("Visible app at index '%d' selected", appIndex));
				break;
			}
			appIndex++;
		}
		
		return theApp;
	}
	
	private int getNumberOfVisibleAppsByThisName() {
		
		Util.loadJsFile(page, "../PlaywrightPOMSeries/src/main/resources/js/util.js", "ictf");
		
		int appIndex = 1;
		int appsWithNameCount = appElements.size();
		int visibleAppsWithNameCount = 0;
		
		for (ElementHandle _appEl : appElements) {
			if ((boolean)page.evaluate("isElementVisible", _appEl)) {
				test.log(Status.INFO, String.format("App '%s' at index '%d/%d' is visible", appName, appIndex, appsWithNameCount));
				visibleAppsWithNameCount++;
			} else {
				test.log(Status.INFO, String.format("App '%s' at index '%d/%d' is not visible", appName, appIndex, appsWithNameCount));
			}
			appIndex++;
		}
		
		return visibleAppsWithNameCount;
	}
	
	private String replaceAllDotWithDash(String _str) {
		String replaced = _str.replaceAll("\\.", "-");
		return replaced;
	}
	
	private String createHighlightElementId() {
		String id = String.format("ictf-highlighted-app--%s", replaceAllDotWithDash(appName));
		return id;
	}
	
	public void setHighlightEnabled(boolean enabled) {
		this.highlightEnabled = enabled;
	}
}
