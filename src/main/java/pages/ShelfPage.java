package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShelfPage extends PageObject {

	@FindBy(xpath = "//img[@title='Remove from my books']")
	private WebElement buttonremove;
	

	public ShelfPage(WebDriver driver) {
		super(driver);
	}


	public void removeBooks() throws InterruptedException {
		driver.navigate().refresh();
		buttonremove.click();
		Thread.sleep(1000);
		WebDriverWait wait = new WebDriverWait(driver, 3000);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(1000);
		System.out.println("alert accepted");
		driver.navigate().back();
		Thread.sleep(1000);
		System.out.println("back from remove books");
		Thread.sleep(10000);
	}


}
