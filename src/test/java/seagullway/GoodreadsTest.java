package seagullway;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import actions.CommonActions;
import common.Browser;
import common.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.AuthorizedPage;
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
		Thread.sleep(3000);

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
		Thread.sleep(3000);

//		Registration with captcha
		UserCreatePage userCreatePage = new UserCreatePage(driver);
		CommonActions.fillForm(driver);
		userCreatePage.enterPassword("test1029384756");
		System.out.println("Waiting for a captcha input in 30 seconds...");
		Thread.sleep(300000);
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
		assertTrue("Authorization passed", driver.getCurrentUrl().equals("https://www.goodreads.com/user/sign_in?source=home"));
		System.out.println("Authorization failed as expected");
		Thread.sleep(3000);

	}

	@Test
	public void testValidSignIn() throws InterruptedException {
//		SignIn with valid credentials
		MainPage mainPage = new MainPage(driver);
		mainPage.enterSignInEmail();
		mainPage.enterSignInPassword();
		mainPage.enterGrButton();
		Thread.sleep(3000);
		AuthorizedPage authorizedPage = new AuthorizedPage(driver);
		authorizedPage.enterCloseButton();
		assertTrue("Authorization did not pass", driver.getTitle().toString().equals("Recent updates | Goodreads"));
		System.out.println("Authorization passed");
		Thread.sleep(3000);
		driver.close();
	}
	
	@Test
	public void testSearchResults() throws InterruptedException {
//		Search for "Best crime and mystery books"
		MainPage mainPage = new MainPage(driver);
		mainPage.enterSignInEmail();
		mainPage.enterSignInPassword();
		mainPage.enterGrButton();
		Thread.sleep(3000);
		AuthorizedPage authorizedPage = new AuthorizedPage(driver);
		authorizedPage.enterCloseButton();
		Thread.sleep(3000);
		assertTrue("Authorization did not pass", driver.getTitle().toString().equals("Recent updates | Goodreads"));
		System.out.println("Authorization passed");
		Thread.sleep(3000);
		authorizedPage.fillUpSearch("Best crime and mystery books");
		assertTrue("Results not found", driver.getCurrentUrl().toString().equals("https://www.goodreads.com/search?q=Best+crime+and+mystery+books&qid="));
		System.out.println("Results found");
		Thread.sleep(3000);
		driver.close();
	}

}