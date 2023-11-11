package com.harma.tf.pages;

import com.microsoft.playwright.Page;

public class BasePage {

	// Property(s)
	private Page page;
	
	// Method(s)
	
	// Constructor(s)
	public BasePage(Page page) {
		this.page = page;
	}
	
	public Page getPage() {
		return this.page;
	}
}
