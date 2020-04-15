package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends PageObject {

	@FindBy(id = "user_first_name")
	private WebElement name;
	
	@FindBy(id = "user_email")
	private WebElement email;

	@FindBy(id = "user_password_signup")
	private WebElement password;
	
//	@FindBy(xpath = "//*[@value='Sign up']")
	@FindBy(className = "button")
	private WebElement buttonSignUp;


	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get("https://www.goodreads.com/");
	}


	public void enterPassword(String pwd) {
		password.sendKeys(pwd);

	}

	public void clickButtonSighUp() {
		buttonSignUp.click();		
	}


	

}
