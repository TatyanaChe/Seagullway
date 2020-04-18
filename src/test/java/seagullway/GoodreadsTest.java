package seagullway;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import actions.CommonActions;
import common.Browser;
import common.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AuthorizedPage;
import pages.BookPage;
import pages.MainPage;
import pages.ResultSearchPage;
import pages.UserCreatePage;

public class GoodreadsTest {

	private WebDriver driver;

	@Before
	public void beforeTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = DriverFactory.get(Browser.chrome);
//		Open the https://www.goodreads.com/
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		String title = driver.getTitle().toString();
		assertTrue("Goodreads is not opened", title.equals("Goodreads | Meet your next favorite book"));
		System.out.println("Goodreads is opened");
		Thread.sleep(1000);

	}

	@After
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void testRegistration() throws InterruptedException, IOException {
		System.out.println("Starting registration .. ");
//		Fill up registration form
		MainPage mainPage = new MainPage(driver);
		CommonActions.fillForm(driver);
		mainPage.enterPassword("test1029384756");
		mainPage.clickButtonSighUp();
		Thread.sleep(1000);

//		Registration with captcha
		UserCreatePage userCreatePage = new UserCreatePage(driver);
		CommonActions.fillForm(driver);
		userCreatePage.enterPassword("test1029384756");
		System.out.println("Waiting for a captcha input in 30 seconds...");
		Thread.sleep(60000);
		assertTrue("Registration is incomplete",
				driver.getCurrentUrl().toString().equals("https://www.goodreads.com/user/create_p2"));
		System.out.println("Registration complete");
		driver.close();
	}

	@Test
	public void testInvalidSignIn() throws InterruptedException {
//		SignIn with invalid credentials
		MainPage mainPage = new MainPage(driver);
		mainPage.enterSignInEmail();
		mainPage.enterSignInWrongPassword();
		mainPage.enterGrButton();
		System.out.println("title: " + driver.getTitle());
		assertTrue("Authorization passed",
				driver.getCurrentUrl().equals("https://www.goodreads.com/user/sign_in?source=home"));
		System.out.println("Authorization failed as expected");
		Thread.sleep(1000);

	}

	@Test
	public void testValidSignIn() throws InterruptedException {
//		SignIn with valid credentials
		MainPage mainPage = new MainPage(driver);
		mainPage.enterSignInEmail();
		mainPage.enterSignInPassword();
		mainPage.enterGrButton();
		Thread.sleep(1000);
		AuthorizedPage authorizedPage = new AuthorizedPage(driver);
		authorizedPage.enterCloseButton();
		assertTrue("Authorization did not pass", driver.getTitle().toString().equals("Recent updates | Goodreads"));
		System.out.println("Authorization passed");
		Thread.sleep(1000);
		driver.close();
	}

	@Test
	public void testSearchResults() throws InterruptedException {
//		Search for "Best crime and mystery books"
		MainPage mainPage = new MainPage(driver);
		mainPage.enterSignInEmail();
		mainPage.enterSignInPassword();
		mainPage.enterGrButton();
		Thread.sleep(1000);
		AuthorizedPage authorizedPage = new AuthorizedPage(driver);
		if (driver.findElement(By.xpath("//*[@class='gr-button gr-button--large']")).isDisplayed()) {
			authorizedPage.enterCloseButton();
		}
		
//		authorizedPage.enterCloseButton();
		Thread.sleep(1000);
		assertTrue("Authorization did not pass", driver.getTitle().toString().equals("Recent updates | Goodreads"));
		System.out.println("Authorization passed");
		Thread.sleep(1000);
		authorizedPage.fillUpSearch("Best crime and mystery books");
		assertTrue("Results not found", driver.getCurrentUrl().toString()
				.equals("https://www.goodreads.com/search?q=Best+crime+and+mystery+books&qid="));
		System.out.println("Results found");
		Thread.sleep(1000);
		
//		List top 3 books
		ResultSearchPage resultSearchPage = new ResultSearchPage(driver);
		List<String> bookHrefList = resultSearchPage.listFirstNumberResultsAsWantToRead(3);
		System.out.println("First books found");
		
		// Mark books
		BookPage bookPage = new BookPage(driver);
		for (String bookHref : bookHrefList) {
			System.out.println("bookHref: " + bookHref);
			driver.get(bookHref);
			bookPage.markBooksWantToRead();
			driver.navigate().refresh();
			WebElement el = driver.findElement(By.xpath("//button[@title='Remove this book from your shelves']"));
			assertTrue("Book did not mark as want to read", el.isDisplayed());
			System.out.println("Book marked as want to read");
			;

		}
//		6. Mark as read
		for (String bookHref : bookHrefList) {
			System.out.println("bookHref: " + bookHref);
			driver.get(bookHref);
			bookPage.markAsRead();
			WebElement el = driver.findElement(By.xpath("//span[@title='Read']"));
			assertTrue("Book did not mark as read", el.isDisplayed());
			System.out.println("Book marked as read");
			Thread.sleep(1000);

		}
//		7. Rate and leave feedback for them (add different read dates)
		for (String bookHref : bookHrefList) {
			System.out.println("bookHref: " + bookHref);
			driver.get(bookHref);
			bookPage.writeReview();
			Thread.sleep(1000);

		}
//		8. Logout
		bookPage.logout();
		assertTrue("Sign out failed", driver.getCurrentUrl().equals("https://www.goodreads.com/user/sign_out"));
		System.out.println("Sign out successful");
		
		Thread.sleep(1000);
		driver.close();
	}
}