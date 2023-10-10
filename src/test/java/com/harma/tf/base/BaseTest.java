package com.harma.tf.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.harma.tf.factory.PlaywrightFactory;
import com.harma.tf.factory.PropertiesFactory;
import com.harma.tf.pages.HomePage;
import com.microsoft.playwright.Page;

public class BaseTest {

	/*
	 * protected PlaywrightFactory pf; protected Page page; protected HomePage
	 * homePage; protected Properties props;
	 * 
	 * @BeforeTest public void setup() { PropertiesFactory propFactory = new
	 * PropertiesFactory("config.properties"); props = propFactory.getProperties();
	 * pf = new PlaywrightFactory(); String browserName =
	 * props.getProperty("browser").trim(); page = pf.initBrowser(browserName);
	 * homePage = new HomePage(page); }
	 * 
	 * @AfterTest public void teardown() { page.context().browser().close(); }
	 */
	
	PlaywrightFactory pf;
	protected Page page;
	protected Properties props;

	protected HomePage homePage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		pf = new PlaywrightFactory();

		props = new PropertiesFactory("config.properties").getProperties();

		if (browserName != null) {
			props.setProperty("browser", browserName);
		}

		page = pf.initBrowser(props);
		homePage = new HomePage(page);
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
}
