package com.harma.tf.tests;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.harma.tf.base.BaseTest;
import com.harma.tf.commands.GotoUrl;
import com.harma.tf.iclego.InputApp;
import com.harma.tf.iclego.ListApp;
import com.harma.tf.listeners.ExtentReportListener;
import com.harma.tf.pages.LoginPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.WaitForURLOptions;

public class IC_10c_uat16_sdkangTest extends BaseTest {

	
	
	@Test
	public void IC10cuat16sdkangTest() {
		
		ExtentTest test = ExtentReportListener.extentTest;
		String appName;
		InputApp inputApp;
		ListApp listApp;
		String _url;
		
		_url = "https://ic10cuat16sdkang.investcloud.com/";
		new GotoUrl(page, test, _url);
		
		LoginPage loginPage = new LoginPage(page, test);
		loginPage.fillOutUsername("client@ic.com");
		loginPage.fillOutPassword("Gray#Wolf14");
		loginPage.clickSignInButton();
		
		WaitForURLOptions waitForURLOptions = new Page.WaitForURLOptions();
		waitForURLOptions.setTimeout(60000);
		page.waitForURL("**/ui/icclientwfapp/cpidynamicdashboardholderapp", waitForURLOptions);
		
		// CPIDashAccounts.List.App
		appName = "CPIDashAccounts.List.App";
		listApp = new ListApp(page, test, appName);
		listApp.singleInstanceExists();
		listApp.isVisible();
		listApp.scrollIntoViewIfNeeded();
		listApp.containsText_ignoringCaseAndSpecialChars2("Richard Lumb");
		listApp.containsText_ignoringCaseAndSpecialChars2("john Meriwether");
		listApp.highlightApp();
		listApp.highlightApp();
		
	}
	
}
