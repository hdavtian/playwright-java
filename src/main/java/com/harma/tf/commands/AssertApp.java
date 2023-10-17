package com.harma.tf.commands;

import static org.testng.Assert.assertTrue;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.harma.tf.utils.Util;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AssertApp extends BaseCommand {
	
	private String appName;
	private String selector;
	private Locator appLocator;
	private ElementHandle appElement;
	
	// Constructor(s)
	public AssertApp(Page page, ExtentTest test, String appName) {
		super("Assert App", page, test);
		this.appName = appName;
		this.selector = "div[data-app='" + appName + "']";
		this.appLocator = page.locator(selector);
		this.appElement = page.querySelector(selector);
	}
	
	// methods
	public String getName() {
		return this.appName;
	}
	
	public boolean singleInstanceExists() {
		int count = appLocator.count();
		boolean onlyOneFound = (count == 1) ? true : false;
		test.log(Status.INFO, "App '" + appName + "' exists as a single web element: " + Boolean.toString(onlyOneFound) + ", element count: " + Integer.toString(count));
		return onlyOneFound;
	}
	
	public boolean isVisible() {
		String locator = "div[data-app='" + appName + "']";
		Boolean isVisible = appLocator.isVisible();
		test.log(Status.INFO, "App '" + appName + "' is visible: " + Boolean.toString(isVisible));
		//assertTrue(!isVisible(), "assertion failed");
		//assert !isVisible : "Element is visible";
		return isVisible;
	}
	
	public boolean containsText(String expectedText) {
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

	
	
	
}
