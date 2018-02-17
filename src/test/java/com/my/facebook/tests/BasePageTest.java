package com.my.facebook.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.my.base.TestBaseSetup;
import com.my.facebook.page.BasePage;

public class BasePageTest extends TestBaseSetup{
	
	private WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver=getDriver();
	}
	
	@Test
	public void verifyHomePage() {
		System.out.println("verifyHomePage...");
		BasePage basePage = new BasePage(driver);
		Assert.assertTrue(basePage.verifyBasePageTitle(),"Facebook - Log In or Sign Up");
	}
	
	@Test
	public void nameBoxTest() {
		System.out.println("nameBoxTest...");
		BasePage basePage = new BasePage(driver);
		basePage.fillFirstNameBox(driver,"Joe");
		//Assert.assertTrue(basePage.verifyBasePageTitle(),"Facebook - Log In or Sign Up");
	}

}