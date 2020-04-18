package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserCreatePage extends PageObject {

	@FindBy(id = "user_password")
	private WebElement password;

	public UserCreatePage(WebDriver driver) {
		super(driver);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);
	}

	public void checkBoxCaptchaOn(WebDriver driver) throws InterruptedException {
		driver.findElement(By.id("recaptcha-anchor")).click();
	}

}
