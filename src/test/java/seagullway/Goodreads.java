package seagullway;


import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import actions.CommonActions;
import common.Browser;
import common.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.MainPage;
import pages.UserCreatePage;

public class Goodreads {
	
	private WebDriver driver;
	
	@Before
	public void beforeTest() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = DriverFactory.get(Browser.chrome);

	}

	@After
	public void afterTest() {
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void test() throws InterruptedException, IOException {
		System.out.println("starting test .. ");
//		Open the https://www.goodreads.com/
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		String title = driver.getTitle().toString();
		assertTrue("goodreads is opened", title.equals("Goodreads | Meet your next favorite book"));
		Thread.sleep(1000);
//		Sign up
		CommonActions.fillForm(driver);
		mainPage.enterPassword("test1029384756");
		mainPage.clickButtonSighUp();
		Thread.sleep(3000);

//		Registration with captcha
		CommonActions.fillForm(driver);
		Thread.sleep(3000);
		UserCreatePage userCreatePage = new UserCreatePage(driver);
		userCreatePage.enterPassword("test1029384756");
		Thread.sleep(5000);
				
}
}