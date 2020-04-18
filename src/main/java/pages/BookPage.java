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

public class BookPage extends PageObject {

	@FindBy(xpath = "//button[@class='wtrToRead']/span[text()='Want to Read']")
	private WebElement buttonWantToRead;
	
	@FindBy(xpath = "//a[text()='My Books']")
	private WebElement linkMyBooks;

	@FindBy(xpath = "//button[@title='Remove this book from your shelves']")
	private WebElement buttonRemoveBook;

	@FindBy(xpath = "//button[@class='wtrShelfButton']")
	private WebElement buttonWtrShelf;

	@FindBy(xpath = "//button[@class='wtrExclusiveShelf']")
	private WebElement buttonWtrExclusiveShelf;

//	@FindBy(xpath = "//a[text()='Write a review']")
	@FindBy(xpath = "//table[@class='myActivity']/tbody/tr[6]/td[2]/a")
	private WebElement buttonWriteReview;

	public BookPage(WebDriver driver) {
		super(driver);
	}

	public void markBooksWantToRead() throws InterruptedException {
		buttonWantToRead.click();
		System.out.println("buttonWantToRead.click() " + driver.getTitle());
		
		//Delete from shelf
//		linkMyBooks.click();
//		Thread.sleep(1000);
//		ShelfPage shelfPage = new ShelfPage(driver);
//		Remove books from shelf
//		shelfPage.removeBooks();
//		System.out.println("shelfPage.removeBooks");
//		Thread.sleep(1000);
//		driver.navigate().back();
//		System.out.println("back from shelf page");
		Thread.sleep(3000);
	}

	public void markAsRead() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorButtonWtrShelf = By.xpath("//button[@class='wtrShelfButton']");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonWtrShelf));

		Actions action = new Actions(driver);
		action.moveToElement(buttonWtrShelf);
		Thread.sleep(3000);
		System.out.println("buttonWtrShelf");
		action.perform();
		Thread.sleep(3000);
		buttonWtrExclusiveShelf.click();
		
		
		Thread.sleep(3000);
		
		
	}

	public void writeReview() throws InterruptedException {
		buttonWriteReview.click();
		Thread.sleep(3000);
		WriteReviewPage writeReviewPage = new WriteReviewPage(driver);
		writeReviewPage.rateBook();
		System.out.println("writeReviewPage.rateBook()");
		Thread.sleep(3000);
		writeReviewPage.writeFeedback();
		System.out.println("writeReviewPage.writeFeedback");
		
	}

}
