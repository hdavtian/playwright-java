package com.harma.tf.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.harma.tf.factory.PlaywrightFactory;
import com.harma.tf.factory.PropertiesFactory;
import com.harma.tf.pages.HomePage;
import com.microsoft.playwright.Page;

public class BaseTest {

	PlaywrightFactory pf;
	protected Page page;
	protected Properties props;
	protected static ExtentReports extent;
	protected static boolean appHighlightEnabled;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		
		pf = new PlaywrightFactory();
		props = new PropertiesFactory("config.properties").getProperties();

		ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		extent = new ExtentReports();
        extent.attachReporter(spark);
		
		if (browserName != null) {
			props.setProperty("browser", browserName);
		}

		page = pf.initBrowser(props);
		
		appHighlightEnabled = false;
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
}
