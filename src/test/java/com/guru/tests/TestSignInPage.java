package com.guru.tests;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.my.config.GlobalDataStore;
import com.guru.pages.GuruHomePage;
import com.guru.pages.SignInPage;

public class TestSignInPage {
	GlobalDataStore gds = new GlobalDataStore();
	String Username;
	String Psswd;
	String GecKoDriver;
	WebDriver driver = null;
	String HomePage;
	String ChromeDriver;
	SignInPage GBPage;
	GuruHomePage GBHomePage;

	/**
	 * Initilaizes the gecko driver object and all the variables needed.
	 **/

	@Parameters({ "BrowserName" })
	@BeforeClass
	public void setUp(@Optional("Firefox") String BrowserName) {

		System.out.println(" The Browser Name " + BrowserName);
		gds.initParameters();
		Username = GlobalDataStore.UserName;
		Psswd = GlobalDataStore.Password;
		GecKoDriver = GlobalDataStore.GeckoDriver_WIN;
		HomePage = GlobalDataStore.HomePage;
		System.out.println("The HomePage " + HomePage);
		System.out.println(" The GeckoDriver " + GecKoDriver);

		if (BrowserName.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", GecKoDriver);
			driver = new FirefoxDriver();
		} else if (BrowserName.equalsIgnoreCase("chrome")) {

			System.out.println(" The chrome Driver " + GlobalDataStore.ChromeDriver_WIN);
			System.setProperty("webdriver.chrome.driver", GlobalDataStore.ChromeDriver_WIN);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.addArguments("--start-maximized");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			// driver.navigate().to(HomePage);

		} else if (BrowserName.equalsIgnoreCase("safari")) {

			driver = new SafariDriver();
			driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
			// driver.navigate().to(HomePage);
		}

	}

	@Test
	public void testGuruSignupPage() {

    GBPage = new SignInPage(driver);
    GBPage.launchHomePage(HomePage);
    //Verify login page title

    String loginPageTitle = GBPage.getLoginTitle();
    System.out.println(loginPageTitle);
    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));

    //login to application
    GBPage.loginToGuru(Username, Psswd);

    // go the next page

    GBHomePage = new GuruHomePage(driver);

    //Verify home page
    Assert.assertTrue(GBHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mngr119144"));

	}

	@AfterClass
	public void afterClass( ) {
		// Close
	//	driver.close();
	}
}
