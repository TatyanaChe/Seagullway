package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultSearchPage extends PageObject {

	
	@FindBy(xpath = "//*[@class='gr-button gr-button--large']")
	private WebElement grIconButton;

	
	public ResultSearchPage(WebDriver driver) {
		super(driver);
	}


}
