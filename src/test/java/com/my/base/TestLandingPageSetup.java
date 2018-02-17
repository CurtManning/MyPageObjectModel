package com.my.base;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.my.config.GlobalDataStore;

public class TestLandingPageSetup {
	GlobalDataStore gds = new GlobalDataStore();
	static String GecKoDriver;
	private static WebDriver driver = null;
	String HomePage;
	String ChromeDriver;
	// private WebDriver driver;
	// static String driverPath =
	// "C:/Users/CurtA/SQA-Workspace/SQASeleniumProject/drivers/";

	public WebDriver getDriver() {
		return driver;
	}

	private void setDriver(String browserType, String appURL) {
		String OS = OSDetector();
		String UserDir = System.getProperty("user.dir");
		browserType = browserType.toLowerCase();
		switch (browserType) {
		case "chrome":
			driver = initChromeDriver(appURL, OS, UserDir);
			break;
		case "firefox":
			driver = initFirefoxDriver(appURL, OS, UserDir);
			break;
		case "safari":
			driver = initSafariDriver(appURL, OS, UserDir);
			break;
		case "edge":
			driver = initEdgeDriver(appURL, OS, UserDir);
			break;
		default:
			System.out.println("browser : " + browserType + " is invalid, Launching Firefox as browser of choice..");
			driver = initFirefoxDriver(appURL, OS, UserDir);
		}
	}

	private static WebDriver initChromeDriver(String appURL, String OS, String UserDir) {

		if (OS.equals("Mac")) {

			System.setProperty("webdriver.chrome.driver", UserDir + GlobalDataStore.ChromeDriver_MAC);

		}

		if (OS.equals("Windows")) {

			// Log.info("Windows chrome Browser ");
			System.setProperty("webdriver.chrome.driver", UserDir + GlobalDataStore.ChromeDriver_WIN);

		}
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-infobars");
		options.addArguments("--start-maximized");
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initFirefoxDriver(String appURL, String OS, String UserDir) {

		if (OS.equals("Mac")) {
			System.out.println("In Fire fox Driver and Mac " + UserDir + GlobalDataStore.GeckoDriver_MAC);
			System.setProperty("webdriver.gecko.driver", UserDir + GlobalDataStore.GeckoDriver_MAC);
		} else {
			System.out.println("In Fire fox Driver");
			System.setProperty("webdriver.gecko.driver", UserDir + GlobalDataStore.GeckoDriver_WIN);
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initSafariDriver(String appURL, String OS, String UserDir) {
		System.out.println("Safari Driver Under Construction...");

		WebDriver driver = new SafariDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.navigate().to(appURL);
		return driver;
	}

	private static WebDriver initEdgeDriver(String appURL, String OS, String UserDir) {
		System.out.println("In Edge Driver under construction...");
		// String serverPath = "C:\\Program Files (x86)\\Microsoft Web
		// Driver\\MicrosoftWebDriver.exe";
		// System.setProperty("webdriver.edge.driver", UserDir +
		// GlobalDataStore.EDGE_DRIVER);
		driver = new EdgeDriver();
		return driver;
	}

	public String OSDetector() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.contains("win")) {
			return "Windows";
		} else if (os.contains("nux") || os.contains("nix")) {
			return "Linux";
		} else if (os.contains("mac")) {
			return "Mac";
		} else if (os.contains("sunos")) {
			return "Solaris";
		} else {
			return "Other";
		}
	}

	@Parameters({ "BrowserName" })
	@BeforeClass
	public void setUp(@Optional("Firefox") String BrowserName) {
		System.out.println(" The Browser Name " + BrowserName);
		gds.initParameters();
		GecKoDriver = GlobalDataStore.GeckoDriver_WIN;
		HomePage = GlobalDataStore.HomePage;
		try {
			setDriver(BrowserName, HomePage);

		} catch (Exception e) {
			System.out.println("Error....." + e.getStackTrace());
		}
	}

	@AfterClass
	public void tearDown() {
		// driver.quit();
	}
}
