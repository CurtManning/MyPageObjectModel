package com.my.facebook.tests;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.my.base.TestLandingPageSetup;
import com.my.facebook.page.LandingPageFactory;


public class LandingPageTest extends TestLandingPageSetup {

//	private static final Logger log = LogManager.getLogger(LandingPageTest.class.getName());

	private WebDriver driver;
	LandingPageFactory landingPage;

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "Joe", "Manning", "test@gmail.com", "7777", "7777777?", "M"}, 
			{ "Sally", "Smith", "ssmith@yahoo.com", "4444", "4444444-", "F"},
			{ "Carol", "MacDuffy", "surf@sbcglobal.net", "1111", "2222222!", "F"} };

	}

	@BeforeClass
	public void setUp() {
	
		driver = getDriver();

	}
	


	/**
	 * This test goes to http:www.facebook.com and verifies the login page title.
	 * @throws InterruptedException 
	 */

	@Test(dataProvider = "getData")
	public void test_Home_Page_Appear_Correct(String firstName, String lastName, String email, String password1, String password2, String gender) throws InterruptedException {
		// Create Login Page object
		landingPage = new LandingPageFactory(driver);
		String loginPageTitle = landingPage.getLoginTitle();
		Thread.sleep(1000);
		landingPage.clickCreateAcct();
		Thread.sleep(2000);
		landingPage.setFirstName(firstName);
		Thread.sleep(1000);
		landingPage.setLastName(lastName);
		Thread.sleep(1000);
		landingPage.setMobileOrEmail(email);
		Thread.sleep(1000);
		landingPage.setPassword(password1);
		Thread.sleep(2000);
		landingPage.setPassword(password2);
		Thread.sleep(2000);
		landingPage.getMonths();
		if (gender == "M") {
			landingPage.clickGenderFemale();
			Thread.sleep(2000);
			landingPage.clickGenderMale();
		} else {
			landingPage.clickGenderMale();
			Thread.sleep(2000);
			landingPage.clickGenderFemale();
		}

		Thread.sleep(2000);

		Assert.assertTrue(loginPageTitle.contains("Facebook - Log In or Sign Up"));
	
	}
	

	@Test
	public void test_Birth_Dates () throws InterruptedException {
		// Create Login Page object
		landingPage = new LandingPageFactory(driver);
		String loginPageTitle = landingPage.getLoginTitle();
		List<WebElement> elementYearList = landingPage.getYears();
		int size = elementYearList.size();
		int FOUR_YEARS = 4;
		for (int i = 1; i < FOUR_YEARS; i++) {   //  replace FOUR_YEARS with size to scroll through everything
			
			int intYear = Integer.parseInt(elementYearList.get(i).getText());
			landingPage.clickBirthYear(intYear);
			
			for (int b = 1; b < 12; b++) {
				landingPage.clickBirthMonth(b); 
				List<WebElement> elementDays =landingPage.getDays();
				int sizeDay = elementDays.size();
	
				for (int c = 1; c <= sizeDay -1; c++) {
					landingPage.clickDays(c);
					if (!landingPage.isDateValid(intYear, b, c)) {
						Reporter.log("Invalid Date " + b + "-" + c + "-" + intYear, true);
					}
				}
			}
			
		}
		//Assert.assertTrue(numberOfMonths == 13);

		Thread.sleep(2000);

		Assert.assertTrue(loginPageTitle.contains("Facebook - Log In or Sign Up"));
	
	}

		@AfterClass
		public void afterClass( ) {
			// Close
			this.driver.close();
			this.driver = null;
			
	}
	
}
