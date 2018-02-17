package com.my.base;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.my.config.GlobalDataStore;

public class TestBaseSetup {
	GlobalDataStore gds = new GlobalDataStore();
	static String GecKoDriver;
	private WebDriver driver = null;
	String HomePage;
	String ChromeDriver;
	//private WebDriver driver;
	//static String driverPath = "C:/Users/CurtA/SQA-Workspace/SQASeleniumProject/drivers/";

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		browserType = browserType.toLowerCase();
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL);
			break;
		case "safari":
			driver = initSafariDriver(appURL);
			break;
		default:
			System.out.println("browser : " + browserType
					+ " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL);
		}
	}

	private static WebDriver initChromeDriver(String appURL) {
		
		System.out.println("Launching google chrome with new profile. " + GlobalDataStore.ChromeDriver);
		System.setProperty("webdriver.chrome.driver", GlobalDataStore.ChromeDriver);
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL) {
		System.out.println("Launching Firefox browser..");
		System.setProperty("webdriver.gecko.driver", GecKoDriver);
		
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}
	
	private static WebDriver initSafariDriver(String appURL) {
		System.out.println("Launching Firefox browser..");

		
		WebDriver driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	@Parameters({ "BrowserName" })
	@BeforeClass
	public void setUp(@Optional("Firefox") String BrowserName) {
		System.out.println(" The Browser Name " + BrowserName);
		gds.initParameters();
		GecKoDriver = GlobalDataStore.GeckoDriver;
		HomePage = GlobalDataStore.HomePage;
		try {
			setDriver(BrowserName, HomePage);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}
	
	@AfterClass
	public void tearDown() {
		//driver.quit();
	}
}

