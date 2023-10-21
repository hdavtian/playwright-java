package com.harma.tf.pages;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.harma.tf.utils.Util;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Keyboard;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

	// *********************************************************
	// Properties
	// *********************************************************
	private Page page;
	private ExtentTest test;
	private Locator usernameLocator;
	private Locator passwordLocator;
	private Locator loginButton;
	
	// *********************************************************
	// Constructor(s)
	// *********************************************************
	
	public LoginPage(Page page, ExtentTest test) {
		this.page = page;
		this.test = test;
		this.usernameLocator = getElementOfTagWithPartialClassName("dx-text-box", "class", "username").locator("input");
		this.passwordLocator = getElementOfTagWithPartialClassName("dx-text-box", "class", "password").locator("input");
		this.loginButton = getElementOfTagWithPartialClassName("dx-button", "aria-label", "signin");
	}
	
	// *********************************************************
	// Method(s)
	// *********************************************************
	
	private Locator getElementOfTagWithPartialClassName(String elementOfTag, String attrName, String partialStr) {
		
		Locator dxTextBoxElements = page.locator(elementOfTag);
		String attrValue;
		String _attrValue;
		boolean elFound = false;
		Locator the_El = null;
		
		for (Locator _el : dxTextBoxElements.all()) {
			
			try {
				attrValue = _el.getAttribute(attrName);
				if (attrValue == null) {
					continue;
				}
			} catch (Exception e) {
				continue;
			}
			
			_attrValue = Util.removeSpacesAndSpecialCharacters(attrValue).trim().toLowerCase();
			
			if (_attrValue.indexOf(partialStr) != -1) {
				elFound = true;
				the_El = _el;
				test.log(Status.INFO, String.format("Found element with partial class name '%s'", partialStr));
				break;
			}
			
		}
		
		return the_El;
	}
	
	public void fillOutUsername(String username) {
		this.usernameLocator.fill(username);
		page.keyboard().press("Tab");
	}
	
	public void fillOutPassword(String username) {
		this.passwordLocator.fill(username);
		page.keyboard().press("Tab");
	}
	
	public void clickSignInButton() {
		this.loginButton.click();
	}
	
	
	
}
