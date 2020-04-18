package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WriteReviewPage extends PageObject {

	@FindBy(xpath = "//a[@title='it was amazing']")
	private WebElement buttonWriteReview;
	
	@FindBy(id = "review_review_usertext")
	private WebElement fieldReview;
	
	public WriteReviewPage(WebDriver driver) {
		super(driver);
	}

	public void rateBook() throws InterruptedException {
		buttonWriteReview.click();
		Thread.sleep(3000);
	}

	public void writeFeedback() throws InterruptedException {
		fieldReview.sendKeys("It was amazing");
		Thread.sleep(3000);
		
	}


}
