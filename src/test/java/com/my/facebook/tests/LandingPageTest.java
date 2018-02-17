package com.my.facebook.tests;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.my.base.TestLandingPageSetup;
import com.my.facebook.page.LandingPageFactory;


public class LandingPageTest extends TestLandingPageSetup {

//	private static final Logger log = LogManager.getLogger(LandingPageTest.class.getName());

	private WebDriver driver;
	LandingPageFactory landingPage;

	@BeforeClass
	public void setUp() {
	
		driver = getDriver();

	}

	/**
	 * This test goes to http:www.facebook.com and verifies the login page title.
	 * @throws InterruptedException 
	 */

	@Test
	public void test_Home_Page_Appear_Correct() throws InterruptedException {
		// Create Login Page object
		landingPage = new LandingPageFactory(driver);
		String loginPageTitle = landingPage.getLoginTitle();
		landingPage.setFirstName("Joe");
		landingPage.setLastName("Manning");
		landingPage.setMobileOrEmail("test@gmail.com");
		landingPage.setPassword("7777");
		Thread.sleep(2000);
		landingPage.clearPassword();
		Thread.sleep(2000);
		landingPage.setPassword("7777777?");
		Thread.sleep(2000);
		landingPage.clickGenderMale();
		Thread.sleep(2000);
		landingPage.clickGenderFemale();
		Thread.sleep(2000);
		landingPage.clickGenderMale();
		Thread.sleep(12000);

		Assert.assertTrue(loginPageTitle.contains("Facebook - Log In or Sign Up"));
		
	}
	
}
