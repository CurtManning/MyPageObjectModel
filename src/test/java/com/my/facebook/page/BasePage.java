package com.my.facebook.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
	protected WebDriver driver;
	public static WebElement element = null;
	// private By signInButton = By.linkText("Sign in");
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public void clickImagesLink() {
		//It should have a logic to click on images link
		//And it should navigate to google images page
	}
	public static WebElement firstNameBox(WebDriver driver) {
		element = driver.findElement(By.id("u_0_e"));
		return element;
	}
	public static void fillFirstNameBox(WebDriver driver, String fname) {
		element = firstNameBox(driver);
		element.sendKeys(fname);
	}
	
	public String getPageTitle(){
		String title = driver.getTitle().trim();
		return title;
	}
	
	public boolean verifyBasePageTitle() {
		String expectedPageTitle="Facebook - Log In or Sign Up";
		System.out.println(getPageTitle());
		return getPageTitle().contains(expectedPageTitle);
	}
}
