package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookPage extends PageObject {

	@FindBy(xpath = "//button[@class='wtrToRead']/span[text()='Want to Read']")
	private WebElement buttonWantToRead;

	@FindBy(xpath = "//a[text()='My Books']")
	private WebElement linkMyBooks;

	@FindBy(xpath = "//button[@class='wtrShelfButton']")
	private WebElement buttonWtrShelf;

	@FindBy(xpath = "//button[@class='wtrExclusiveShelf']")
	private WebElement buttonWtrExclusiveShelf;

	@FindBy(xpath = "//table[@class='myActivity']/tbody/tr[6]/td[2]/a")
	private WebElement buttonWriteReview;

	@FindBy(xpath = "//*[@class='rereadDatePicker smallPicker startYear']")
	private WebElement startYear;

	@FindBy(xpath = "//*[@class='rereadDatePicker largePicker startMonth']")
	private WebElement startMonth;

	@FindBy(xpath = "//*[@class='rereadDatePicker smallPicker startDay']")
	private WebElement startDay;

	@FindBy(xpath = "//*[@class='endedAtSetTodayLink gr-button']")
	private WebElement endedAtSetToday;

	@FindBy(xpath = "//*[@value='Post']")
	private WebElement buttonPost;
	
	@FindBy(xpath = "//div[@class='dropdown dropdown--profileMenu']/a")
	private WebElement dropdownMenu;

	private int count = 1;

	public BookPage(WebDriver driver) {
		super(driver);
	}

	public void markBooksWantToRead() throws InterruptedException {
		buttonWantToRead.click();
		System.out.println("buttonWantToRead.click() " + driver.getTitle());
		Thread.sleep(1000);
	}

	public void markAsRead() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorButtonWtrShelf = By.xpath("//button[@class='wtrShelfButton']");
		Thread.sleep(1000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorButtonWtrShelf));

		Actions action = new Actions(driver);
		action.moveToElement(buttonWtrShelf);
		Thread.sleep(1000);
		System.out.println("buttonWtrShelf");
		action.perform();
		Thread.sleep(1000);
		buttonWtrExclusiveShelf.click();
		Thread.sleep(1000);

	}

	public void writeReview() throws InterruptedException {
		buttonWriteReview.click();
		Thread.sleep(1000);
		
//	Rate book
		WriteReviewPage writeReviewPage = new WriteReviewPage(driver);
		writeReviewPage.rateBook();
		System.out.println("writeReviewPage.rateBook()");
		Thread.sleep(1000);
//		Write feedback
		writeReviewPage.writeFeedback();
		System.out.println("writeReviewPage.writeFeedback");
		
//		Start read date
		startYear.click();
		Thread.sleep(1000);
		System.out.println("startYear");
		Select year = new Select(startYear);
		year.selectByVisibleText("2020");
		Thread.sleep(1000);
		System.out.println("startYear set");
		startMonth.click();
		Select month = new Select(startMonth);
		month.selectByVisibleText("March");
		Thread.sleep(1000);
		System.out.println("startMonth set");
		
		startDay.click();
		Select dayStart = new Select(startDay);
		String randomDay = Integer.valueOf(count++ % 28).toString();
		dayStart.selectByVisibleText(randomDay);
		Thread.sleep(1000);
		System.out.println("startDay set");
		
//		End read date
		endedAtSetToday.click();
		Thread.sleep(1000);
		buttonPost.click();
		Thread.sleep(1000);
		driver.navigate().back();
		driver.navigate().back();
		driver.navigate().refresh();
		Thread.sleep(1000);
		
	}
	
//	public void deleteFromShelf() throws InterruptedException {
//		// Delete from shelf
//		linkMyBooks.click();
//		Thread.sleep(1000);
//		ShelfPage shelfPage = new ShelfPage(driver);
//		//Remove books from shelf
//		shelfPage.removeBooks();
//		System.out.println("shelfPage.removeBooks");
//		Thread.sleep(1000);
//		driver.navigate().back();
//		System.out.println("back from shelf page");
//
//	}

	public void logout() throws InterruptedException {
		WebDriverWait wait = new WebDriverWait(driver, 5000);
		By locatorDropdownMenu = By.xpath("//div[@class='dropdown dropdown--profileMenu']/a");
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locatorDropdownMenu));
		
		Actions action = new Actions(driver);
		action.moveToElement(dropdownMenu);
		Thread.sleep(1000);
		action.perform();
		Thread.sleep(1000);
		
		dropdownMenu.click();
		Thread.sleep(1000);
		WebDriverWait waitMenu = new WebDriverWait(driver, 5000);
		System.out.println("waitMenu");
		By locatorUserSignOut = By.xpath("//a[@href='/user/sign_out']");
		Thread.sleep(3000);
		waitMenu.until(ExpectedConditions.visibilityOfElementLocated(locatorUserSignOut));
		
		WebElement btnUserSignOut = driver.findElement(By.xpath("//a[@href='/user/sign_out']"));
		btnUserSignOut.click();
		Thread.sleep(1000);
		
	}

}
