package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AuthorizedPage extends PageObject {

	@FindBy(xpath = "//*[@class='gr-button gr-button--large']")
	private WebElement grIconButton;

	@FindBy(xpath = "//form[@class='searchBox__form']")
	private WebElement searchBoxes;

	public AuthorizedPage(WebDriver driver) {
		super(driver);
	}

	public void open() {
		driver.get("https://www.goodreads.com/user/create_p2");
	}

	public void enterCloseButton() {
		grIconButton.click();
	}

	public void fillUpSearch(String search) {
		searchBoxes.findElements(By.className("searchBox__input")).get(0).sendKeys(search);
		searchBoxes.findElements(By.className("searchBox__icon--magnifyingGlass")).get(0).click();
	}

}
