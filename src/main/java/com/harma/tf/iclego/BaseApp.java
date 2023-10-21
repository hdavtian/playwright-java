package com.harma.tf.iclego;

import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.harma.tf.utils.Util;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public abstract class BaseApp {
	
	// *****************************************************************
	// Properties
	// *****************************************************************
	
	private String appName;
	private String selector;
	private Locator appLocator;
	private List<ElementHandle> appElements;
	private ElementHandle appElement;
	protected Page page;
	protected ExtentTest test;
	
	// *****************************************************************
	// Constructor(s)
	// *****************************************************************
	
	public BaseApp(Page page, ExtentTest test, String appName) {
		
		this.page = page;
		this.test = test;
		this.appName = appName;
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
			this.appElement = getSingleVisibleApp();
		}
		
	}
	
	// *****************************************************************
	// Methods
	// *****************************************************************
	
	public ElementHandle getAppElement() {
		return appElement;
	}
	
	public void scrollIntoViewIfNeeded() {
		appElement.scrollIntoViewIfNeeded();
	}
	
	public void highlightApp() {
		Util.addTFStylesheetToPageIfNecessasry(page);
		page.evalOnSelector(selector, "el => el.classList.add('ictf-highlight-element')");
		test.log(Status.INFO, "Added 'harma' class to element: '" + appName + "'");
	}
	
	public boolean isVisible() {
		//Boolean isVisible = appLocator.isVisible();
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
		// on the page. We cannot rely on basic visibility algos
		Util.loadJsFile(page, "../PlaywrightPOMSeries/src/main/resources/js/util.js", "harma");
		
		/*
		Locator theApp = null;
		List<Locator> allApps = appLocator.all();
		
		int appIndex = 1;
		for (Locator _app : allApps) {
			if ((boolean)page.evaluate("isElementVisible", _app)) {
				theApp = _app;
				test.log(Status.INFO, String.format("Visible app at index '%d' selected", appIndex));
				break;
			}
			appIndex++;
		}
		*/
		
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
}
