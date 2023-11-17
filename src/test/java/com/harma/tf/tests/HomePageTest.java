package com.harma.tf.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.harma.tf.base.BaseTest;
import com.harma.tf.commands.*;
import com.harma.tf.iclego.InputApp;
import com.harma.tf.iclego.ListApp;
import com.harma.tf.listeners.ExtentReportListener;
import com.harma.tf.utils.BrowserReportWindow;

public class HomePageTest extends BaseTest {
	
	@Test
	public void homePageTitleTest(){
		
		ExtentTest test = ExtentReportListener.extentTest;
		String appName;
		InputApp inputApp;
		ListApp listApp;
		String _url;
		
		try {
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// IC homepage
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/";
			new GotoUrl(page, test, _url);
			
			basePage.getAllListApps();
			
			// After visiting a page, the code will automatically capture network requests/responses
			// we can access captured info into a local variable like this
			capturedRequests = networkInterceptor.getCapturedRequests();
			Assert.assertTrue(networkRequestsContainsCallWithString(capturedRequests, "ecdg.ashx?requesttype=dataset&v=2"), "dang!");
			BaseTest.appHighlightEnabled = false;
			
			// new browser window for logging
			BrowserReportWindow reportWindow = new BrowserReportWindow(page, "Reports");
			reportWindow.openWindow();
			
			// Core.Header.Menu.App
			appName = "Core.Header.Menu.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.singleInstanceExists();
			inputApp.scrollIntoViewIfNeeded();
			inputApp.isVisible();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			Assert.assertTrue(inputApp.singleInstanceExists(), "Failed");
			Assert.assertTrue(inputApp.isVisible(), "Failed");
			
			// IC.FooterCopyrt.Input.App
			appName = "IC.FooterCopyrt.Input.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.highlightApp();
			
			// H.Hero.Title.Input.App
			appName = "H.Hero.Title.Input.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.isVisible();
			inputApp.takeScreenshot();
			inputApp.setHighlightEnabled(true);
			inputApp.highlightApp();
			
			Assert.assertFalse(inputApp.containsText_ignoringCaseAndSpecialChars("Welcome to Digita_xyz"));
			Assert.assertTrue(inputApp.containsText_ignoringCaseAndSpecialChars("Welcome to Digital"));
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// About us
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4whowearev2holderapp";
			new GotoUrl(page, test, _url);
			
			capturedRequests = networkInterceptor.getCapturedRequests();
            Assert.assertTrue(networkRequestsContainsCallWithString(capturedRequests, "ecd.ashx?requesttype=dataset&v=2&app=v4TeamMembers_List.App"), "oopsie");
			
			appName = "v4TeamMembers.List.App";
			listApp = new ListApp(page, test, appName);
			listApp.scrollIntoViewIfNeeded();
			listApp.singleInstanceExists();
			listApp.isVisible();
			listApp.takeScreenshot();
			listApp.highlightApp();
			Assert.assertFalse(listApp.containsText_ignoringCaseAndSpecialChars("xyzRichard Lumb"));
			Assert.assertTrue(listApp.containsText_ignoringCaseAndSpecialChars("Richard Lumb"));			
			 
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// Products > Digital Wealth
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=digiwealthholderapp";
			new GotoUrl(page, test, _url);
			
			appName = "DigiWealth.Hero.Desc.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// Products > Digital Planning
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finplanholderapp";
			new GotoUrl(page, test, _url);
			
			appName = "FinPlan.FeatApp1.Text.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// Products > Financial supermarket
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finsupermktplatholderapp";
			new GotoUrl(page, test, _url);
			
			appName = "FinSpmktPlat.Hero.Bg.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// Products > Investcloud App Library
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=appstoreapp";
			new GotoUrl(page, test, _url);
			
			appName = "AppStore.Featured.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// Products > News
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4icnewsholderapp";
			new GotoUrl(page, test, _url);
			
			appName = "v4ICNewsKPI.Input.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			
			// >>>>>>>>>>>>>>>> New page <<<<<<<<<<<<<<<<<<<<<<<
			// Contact us
			// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
			
			_url = "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4contactpageinputapp";
			new GotoUrl(page, test, _url);
			
			appName = "ContactPage.Title.App";
			inputApp = new InputApp(page, test, appName);
			inputApp.scrollIntoViewIfNeeded();
			inputApp.singleInstanceExists();
			inputApp.takeScreenshot();
			inputApp.highlightApp();
			
			
			
			//boolean CoreAppExists = Util.appExists(page, "Core.Header.Menu.App");
			
			
//			String actualTitle = homePage.getPageTitle();
//			Assert.assertEquals(actualTitle, "InvestCloud", "my custom message");
//			test.log(Status.INFO, "Assertion for title successful");
//			page.evaluate("$('div[data-app]').css('border','1px solid red')");
//			test.log(Status.INFO, "Highlighted all divs with data-app attribute");
//			page.wait(5000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		/*
		synchronized (page) {
			
			//GotoUrl gotoUrl = new GotoUrl(page, test, "https://www.investcloud.com/");
			new GotoUrl(page, test, "https://www.investcloud.com/");
			
			// assertAppCmdExists
			assertAppCmd assertAppCmd = new assertAppCmd(page, test, "Core.Header.Menu.App");
			assertAppCmd.exists();
			assertAppCmd.isVisible();
			
			try {
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
			
	}

}
