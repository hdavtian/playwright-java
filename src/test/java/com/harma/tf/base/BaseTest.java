package com.harma.tf.base;

import java.util.List;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.harma.tf.factory.PlaywrightFactory;
import com.harma.tf.factory.PropertiesFactory;
import com.harma.tf.pages.BasePage;
import com.harma.tf.utils.NetworkInterceptor;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Request;

public class BaseTest {

	private PlaywrightFactory pf;
	protected BasePage basePage;
	protected Page page;
	//private Page page;
	protected Properties props;
	protected static ExtentReports extent;
	protected static boolean appHighlightEnabled;
	protected NetworkInterceptor networkInterceptor;
	protected List<Request> capturedRequests;

	@Parameters({ "browser" })
	@BeforeTest
	public void setup(String browserName) {
		
		props = new PropertiesFactory("config.properties").getProperties();

		ExtentSparkReporter spark = new ExtentSparkReporter("Spark.html");
		extent = new ExtentReports();
        extent.attachReporter(spark);
		
		if (browserName != null) {
			props.setProperty("browser", browserName);
		}

		pf = new PlaywrightFactory();
		pf.setupBrowser(props);
		pf.setupBrowserContext();
		pf.setupPlaywrightPage();
		page = pf.getPage();
		
		basePage = new BasePage(page);
		
		// Create a NetworkInterceptor
        networkInterceptor = new NetworkInterceptor();
        
        // Enable request interception
        pf.getBrowserContext().route("**/*", route -> {
            networkInterceptor.accept(route.request());
            route.resume();
        });
		
		int vpWidth = page.viewportSize().width;
		int vpHeight = page.viewportSize().height;
		page.setViewportSize(vpWidth, vpHeight);
		
		appHighlightEnabled = false;
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
	
	public boolean networkRequestsContainsCallWithString(List<Request> capturedRequests, String partialStr) {
		boolean found = false;
		for (Request request : capturedRequests) {
            if (request.url().contains(partialStr)) {
            	//extent.log(Status.INFO, "API call detected: " + request.url());
            	found = true;
            }
        }
		return found;
	}
	
	public PlaywrightFactory getPlaywrightFactory() {
		return this.pf;
	}
}
