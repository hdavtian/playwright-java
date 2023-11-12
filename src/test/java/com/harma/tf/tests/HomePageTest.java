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
			
//			assertAppCmd = new Command(page, test, "DigiWealth.Hero.Desc.App");
//			assertAppCmd.singleInstanceExists();
//			assertAppCmd.isVisible();
			
			/*
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finplanholderapp
			// FinPlan.FeatApp1.Text.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finplanholderapp");
			
			assertAppCmd = new Command(page, test, "FinPlan.FeatApp1.Text.App");
			assertAppCmd.singleInstanceExists();
			assertAppCmd.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finsupermktplatholderapp
			// FinSpmktPlat.Hero.Bg.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finsupermktplatholderapp");
			
			assertAppCmd = new Command(page, test, "FinSpmktPlat.Hero.Bg.App");
			assertAppCmd.singleInstanceExists();
			assertAppCmd.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=appstoreapp
			// AppStore.Featured.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=appstoreapp");
			
			assertAppCmd = new Command(page, test, "AppStore.Featured.App");
			assertAppCmd.singleInstanceExists();
			assertAppCmd.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4icnewsholderapp
			// v4ICNewsKPI.Input.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4icnewsholderapp");
			
			assertAppCmd = new Command(page, test, "v4ICNewsKPI.Input.App");
			assertAppCmd.singleInstanceExists();
			assertAppCmd.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4contactpageinputapp
			// ContactPage.Title.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4contactpageinputapp");
			
			assertAppCmd = new Command(page, test, "ContactPage.Title.App");
			assertAppCmd.singleInstanceExists();
			assertAppCmd.isVisible();
			
			// ************************************************************************
			
			*/
			
			
			
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
