package com.harma.tf.factory;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Properties;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.options.ViewportSize;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightFactory {

	private static Playwright playwright;
	private static Browser browser;
	private static BrowserContext browserContext;
	private static Page page;
	
	//private static ThreadLocal<Browser> tlBrowser = new ThreadLocal<>();
	//private static ThreadLocal<BrowserContext> tlBrowserContext = new ThreadLocal<>();
	//private static ThreadLocal<Page> tlPage = new ThreadLocal<>();
	//private static ThreadLocal<Playwright> tlPlaywright = new ThreadLocal<>();

	public Playwright getPlaywright() {
		return playwright;
	}

	public Browser getBrowser() {
		return browser;
	}

	public BrowserContext getBrowserContext() {
		return browserContext;
	}

	public Page getPage() {
		return page;
	}
	
	public void setPage(Page _page) {
		page = _page;
	}
	
	public void setupBrowser(String browserName) {

		System.out.println("browser name is : " + browserName);

		playwright = Playwright.create();
		//tlPlaywright.set(Playwright.create());

		switch (browserName.toLowerCase()) {
		case "chromium":
			//tlBrowser.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			PlaywrightFactory.browser = getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "firefox":
			//tlBrowser.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			PlaywrightFactory.browser = getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "safari":
			//tlBrowser.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
			PlaywrightFactory.browser = getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
			break;
		case "chrome":
			//tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false)));
			PlaywrightFactory.browser = getPlaywright().chromium().launch(new LaunchOptions().setChannel("chrome").setHeadless(false));
			break;
		case "edge":
			//tlBrowser.set(getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false)));
			PlaywrightFactory.browser = getPlaywright().chromium().launch(new LaunchOptions().setChannel("msedge").setHeadless(false));
			break;	

		default:
			System.out.println("please pass the right browser name......");
			break;
		}
	}

	// Overloaded method accepting a Properties arg (if you want to put things in a prop file)
	public void setupBrowser(Properties prop) {
		String browserName = prop.getProperty("browser").trim();
		setupBrowser(browserName);
	}

	public void setupBrowserContext() {
		NewContextOptions contextOptions = new NewContextOptions();
		browserContext = getBrowser().newContext(contextOptions);
	}
	
	public void setupPlaywrightPage() {
		Page page = getBrowserContext().newPage();
		setPage(page);
	}
	
	/**
	 * take screenshot
	 * 
	 */

	public static String takeScreenshot() {
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		//getPage().screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		
		byte[] buffer = page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(path)).setFullPage(true));
		String base64Path = Base64.getEncoder().encodeToString(buffer);
		
		return base64Path;
	}
}
