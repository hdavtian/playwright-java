package com.harma.tf.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.harma.tf.base.BaseTest;
import com.harma.tf.listeners.ExtentReportListener;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Page.NavigateOptions;

public class HomePageTest extends BaseTest{
	 
	@Test
	public void homePageTitleTest(){
		
		ExtentTest test = ExtentReportListener.extentTest;
		
		synchronized (page) {
			try {
				NavigateOptions navigateOptions = new Page.NavigateOptions();
				navigateOptions.setTimeout(60000);
				page.navigate(props.getProperty("url").trim(), navigateOptions);
				test.log(Status.INFO, "Visited url: " + props.getProperty("url"));
				page.wait(1000);
				String actualTitle = homePage.getPageTitle();
				Assert.assertEquals(actualTitle, "InvestCloud", "my custom message");
				test.log(Status.INFO, "Assertion for title successful");
				page.evaluate("$('div[data-app]').css('border','1px solid red')");
				test.log(Status.INFO, "Highlighted all divs with data-app attribute");
				page.wait(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
