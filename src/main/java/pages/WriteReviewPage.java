package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WriteReviewPage extends PageObject {

	@FindBy(xpath = "//a[@title='it was amazing']")
	private WebElement buttonStarOff;
	
	@FindBy(id = "review_review_usertext")
	private WebElement fieldReview;
	
	public WriteReviewPage(WebDriver driver) {
		super(driver);
	}

	public void rateBook() throws InterruptedException {
		buttonStarOff.click();
		Thread.sleep(1000);
	}

	public void writeFeedback() throws InterruptedException {
		fieldReview.sendKeys("It was amazing");
		Thread.sleep(1000);
		
	}


}
