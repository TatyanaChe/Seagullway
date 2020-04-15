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

	@FindBy(xpath = "//*[@title='Вход']")
	private WebElement enterLink;
	
	@FindBy(xpath = "//*[@title='Вход']")
	private WebElement login;

	@FindBy(xpath = "//*[@title='Вход']")
	private WebElement password;

	@FindBy(xpath = "//*[@title='Вход']")
	private WebElement blueBtn;

	public MainPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get("https://www.goodreads.com/");
	}

	public String getTransportPage() {
		return driver.getCurrentUrl();
	}

	public void clickLogin() {
		enterLink.click();

	}

	public void enterLogin(String lgn) {
		login.sendKeys(lgn);
	}

	public void enterPassword(String pwd) {
		password.sendKeys(pwd);

	}

	public void clickEnterBtn() {
		blueBtn.click();

	}

}
