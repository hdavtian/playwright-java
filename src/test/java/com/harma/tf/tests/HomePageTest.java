package com.harma.tf.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.harma.tf.base.BaseTest;
import com.harma.tf.commands.*;
import com.harma.tf.listeners.ExtentReportListener;
import com.harma.tf.utils.Util;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.NavigateOptions;
import com.microsoft.playwright.options.LoadState;

public class HomePageTest extends BaseTest{
	 
	@Test
	public void homePageTitleTest(){
		
		ExtentTest test = ExtentReportListener.extentTest;
		
		try {
			
			//GotoUrl gotoUrl = new GotoUrl(page, test, "https://www.investcloud.com/");
			new GotoUrl(page, test, "https://www.investcloud.com/");
			
			// AssertAppExists
			AssertApp assertApp = new AssertApp(page, test, "Core.Header.Menu.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			assertApp = new AssertApp(page, test, "H.Hero.Title.Input.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			assertApp.containsText("Harma");
			assertApp.containsText("Welcome to Digita_l");
			assertApp.containsText("Welcome to Digital");
			
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4whowearev2holderapp");
			
			assertApp = new AssertApp(page, test, "v4TeamMembers.List.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			
			
			//https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=digiwealthholderapp
			// DigiWealth.Hero.Desc.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=digiwealthholderapp");
			
			assertApp = new AssertApp(page, test, "DigiWealth.Hero.Desc.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finplanholderapp
			// FinPlan.FeatApp1.Text.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finplanholderapp");
			
			assertApp = new AssertApp(page, test, "FinPlan.FeatApp1.Text.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finsupermktplatholderapp
			// FinSpmktPlat.Hero.Bg.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=finsupermktplatholderapp");
			
			assertApp = new AssertApp(page, test, "FinSpmktPlat.Hero.Bg.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=appstoreapp
			// AppStore.Featured.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=appstoreapp");
			
			assertApp = new AssertApp(page, test, "AppStore.Featured.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4icnewsholderapp
			// v4ICNewsKPI.Input.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4icnewsholderapp");
			
			assertApp = new AssertApp(page, test, "v4ICNewsKPI.Input.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			// https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4contactpageinputapp
			// ContactPage.Title.App
			new GotoUrl(page, test, "https://www.investcloud.com/Membership/Apps/v4IcHomePage_WF_App.aspx#!/w/v4ichomepagewfapp?s=v4contactpageinputapp");
			
			assertApp = new AssertApp(page, test, "ContactPage.Title.App");
			assertApp.singleInstanceExists();
			assertApp.isVisible();
			
			
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
			
			// AssertAppExists
			AssertApp assertApp = new AssertApp(page, test, "Core.Header.Menu.App");
			assertApp.exists();
			assertApp.isVisible();
			
			try {
				
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		*/
			
	}

}
