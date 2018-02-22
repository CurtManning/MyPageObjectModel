package com.my.facebook.page;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

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

	@FindBy(id = "pageTitle")
	WebElement titleText;

	@FindBy(name = "firstname")
	WebElement firstName;

	@FindBy(name = "lastname")
	WebElement lastName;

	@FindBy(name = "reg_email__")
	WebElement mobileOrEmail;

	@FindBy(name = "reg_passwd__")
	WebElement password;

	@FindBy(xpath = "//input[@value='1']")
	WebElement genderFemale;

	@FindBy(xpath = "//input[@value='2']")
	WebElement genderMale;

	@FindBy(name = "websubmit")
	WebElement clickCreateAccount;

	@FindBy(xpath = "//*[@id='month']/option")
	List<WebElement> months;

	@FindBy(xpath = "//*[@id='year']/option")
	List<WebElement> years;

	@FindBy(xpath = "//*[@id='day']/option")
	List<WebElement> days;

	@FindBy(xpath = "//*[@id='year']/option[[@value='Log out']]")
	List<WebElement> byear;

	public List<WebElement> getMonths() {

		List<WebElement> elementList = months;

		// System.out.println("Size of the element list is: " + size);
		return elementList;
	}

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
		firstName.clear();
		firstName.sendKeys(first);
	}

	public void setLastName(String last) {
		log.debug(" setLastName " + last);
		lastName.clear();
		lastName.sendKeys(last);
	}

	public void setMobileOrEmail(String mobileEmail) {
		log.debug(" setMobileOrEmail " + mobileEmail);
		mobileOrEmail.clear();
		mobileOrEmail.sendKeys(mobileEmail);
	}

	public void setPassword(String passwrd) {
		log.debug(" setPassword " + passwrd);
		password.clear();
		password.sendKeys(passwrd);
	}

	public void clearPassword() {
		log.debug(" clearPassword ");
		password.clear();
	}

	public void clickBirthMonth(int bMonth) {

		WebElement month = driver.findElement(By.xpath("//*[@id='month']/option[@value='" + bMonth + "']"));
		// log.debug(" clickMonth " + month);
		month.click();
	}
	
	public void clickBirthYear(int bYear) {
		log.debug(" clickBirthYear ");
		WebElement year = driver.findElement(By.xpath("//*[@id='year']/option[@value='" + bYear + "']"));
		year.click();
	}
	


	public void clickDays(int bday) {

		WebElement day = driver.findElement(By.xpath("//*[@id='day']/option[@value='" + bday + "']"));
		// log.debug(" clickDays " + day);
		day.click();
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

	public void clickCreateAcct() {

		log.debug(" clickCreateAccount ");
		clickCreateAccount.click();
	}

	public List<WebElement> getYears() {

		List<WebElement> elementYearList = years;
		
		return elementYearList;
	}

	public List<WebElement> getDays() {

		List<WebElement> elementDayList = days;
		// int size = elementDayList.size();
		// System.out.println("Size of the element list is: " + size);
		return elementDayList;
	}

	public boolean isDateValid(int year, int month, int day) {

	    boolean dateIsValid = true;
	    try {
	        LocalDate.of(year, month, day);
	    } catch (DateTimeException e) {
	        dateIsValid = false;
	    }
	    return dateIsValid;
	}

}
