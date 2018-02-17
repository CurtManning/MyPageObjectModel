package com.my.facebook.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LandingPageFactory {
	
	private static final Logger log = LogManager.getLogger(LandingPageFactory.class.getName());
	WebDriver driver;

	@FindBy(id="pageTitle")
	WebElement titleText;

	@FindBy(name="firstname")
	WebElement firstName;
	
	@FindBy(name="lastname")
	WebElement lastName;
	
	@FindBy(name="reg_email__")
	WebElement mobileOrEmail;
	
	@FindBy(name="reg_passwd__")
	WebElement password;
	
	@FindBy(xpath="//input[@value='1']")
	WebElement genderFemale;

	@FindBy(xpath="//input[@value='2']")
	WebElement genderMale;
	
	// Get the title of Login Page

	public String getLoginTitle() {
		String title = driver.getTitle().trim();
		log.debug(" getLoginTitle " + title);
		return title;
	}

	public String getFirstName() {
		log.debug(" getFirstName " + firstName.getText());
		return firstName.getText();
	}


	public void setFirstName(String first) {
		log.debug(" setFirstName " + first);
		firstName.sendKeys(first);
	}
	
	public void setLastName(String last) {
		log.debug(" setLastName " + last);
		lastName.sendKeys(last);
	}
	
	public void setMobileOrEmail(String mobileEmail) {
		log.debug(" setMobileOrEmail " + mobileEmail);
		mobileOrEmail.sendKeys(mobileEmail);
	}
	
	public void setPassword(String passwrd) {
		log.debug(" setPassword " + passwrd);
		password.sendKeys(passwrd);
	}
	
	public void clearPassword() {
		log.debug(" clearPassword ");
		password.clear();
	}
	
	public void clickGenderFemale() {
		log.debug(" clickGenderFemale ");
		genderFemale.click();
	}
	
	public void clickGenderMale() {
		log.debug(" clickGenderMale ");
		genderMale.click();
	}
	
	
	public LandingPageFactory(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

}
