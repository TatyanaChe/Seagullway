package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCreatePage extends PageObject {

	@FindBy(id = "user_first_name")
	private static WebElement name;

	@FindBy(id = "user_password")
	private WebElement password;

	public UserCreatePage(WebDriver driver) {
		super(driver);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

}
