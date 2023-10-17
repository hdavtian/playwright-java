package com.harma.tf.commands;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.NavigateOptions;
import com.microsoft.playwright.options.LoadState;

public class GotoUrl extends BaseCommand {
	
	private String url;
	
	// Constructor(s)
	public GotoUrl(Page page, ExtentTest test, String url) {
		
		super("Assert App", page, test);
		this.url = url;
		
		// can pull this into individual methods for this command
		// not yet.
		NavigateOptions navigateOptions = new Page.NavigateOptions();
		navigateOptions.setTimeout(60000);
		//page.navigate(props.getProperty("url").trim(), navigateOptions);
		page.navigate(url, navigateOptions);
		// est.log(Status.INFO, "Visited url: " + props.getProperty("url"));
		test.log(Status.INFO, "Visited url: " + url);
		page.waitForLoadState(LoadState.NETWORKIDLE);
		test.log(Status.INFO, "Wait for state was set to 'NETWORKIDLE'.");
	}

}
