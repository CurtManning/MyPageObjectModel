package com.guru.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {

	protected WebDriver driver;

//	By userName = By.name("uid");
	By userName = By.xpath("//input[@name='uid']");

	By passwordGuru = By.name("password");
	By login = By.name("btnLogin");
	public void launchHomePage(String HomePage) {
		driver.get(HomePage);
		driver.manage().window().maximize();

	}
	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	// Set user name in textbox

	public void setUserName(String strUserName) {
		driver.findElement(userName).sendKeys(strUserName);
	}

	// Set password in password textbox

	public void setPassword(String strPassword) {

		driver.findElement(passwordGuru).sendKeys(strPassword);

	}

	// Click on login button

	public void clickLogin() {

		driver.findElement(login).click();

	}

	// Get the title of Login page
	public String getLoginTitle() {
		String title = driver.getTitle().trim();
		System.out.println(title);
		return title;

	}

	/**
	 * This POM method will be exposed in test case to login in the application
	 */

	public void loginToGuru(String strUserName, String strPasword) {

		// Fill user name
		this.setUserName(strUserName);
		// Fill password
		this.setPassword(strPasword);
		// Click Login button
		this.clickLogin();

	}

}