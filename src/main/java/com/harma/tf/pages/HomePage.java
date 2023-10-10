package com.harma.tf.pages;

import com.microsoft.playwright.Page;

public class HomePage {
	
	private Page page;
	
	// constructor
	public HomePage(Page page) {
		this.page = page;
	}
	
	// selectors
	private String headerLogo = "div[data-app='Core.Header.Logo.App']";
	
	// methods
	public String getPageTitle() {
		String title = page.title();
		System.out.println("page title: " + title);
		return title;
	}
	
	public String getPageUrl() {
		String url = page.url();
		System.out.println("page url" + url);
		return url;
	}
	
	public void clickLogo() {
		page.click(headerLogo);
	}

}
