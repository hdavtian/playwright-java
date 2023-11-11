package com.harma.tf.utils;

import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.options.ColorScheme;
import com.microsoft.playwright.Page;

public class BrowserReportWindow {

	// --------------------------------------------------------------
	// Properties
	// --------------------------------------------------------------

	protected Page page;
	protected String pageTitle;

	// --------------------------------------------------------------
	// Constructor(s)
	// --------------------------------------------------------------

	public BrowserReportWindow(Page page, String title) {
		this.page = page;
		this.pageTitle = title;
	}

	// --------------------------------------------------------------
	// Methods
	// --------------------------------------------------------------

	public void setPageTitle(String title) {
		String jsCode = "document.title = '" + title + "';";
		page.evaluate(jsCode);
	}

	public void openWindow() {

		NewContextOptions contextOptions = new NewContextOptions();
		contextOptions.setColorScheme(ColorScheme.DARK);
		contextOptions.setViewportSize(500, 800);

		page.context().browser().newContext(contextOptions).newPage();
		setPageTitle(pageTitle);
	}

}
