package com.harma.tf.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.harma.tf.base.BaseTest;

public class HomePageTest extends BaseTest{
	 
	@Test
	public void homePageTitleTest(){
		
		synchronized (page) {
			try {
				page.navigate(props.getProperty("url").trim());
				page.wait(1000);
				String actualTitle = homePage.getPageTitle();
				Assert.assertEquals(actualTitle, "InvestCloud", "my custom message");
				page.evaluate("$('div[data-app]').css('border','1px solid red')");
				page.wait(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}

}
