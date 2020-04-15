package seagullway;


import java.io.IOException;

import pages.MainPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import common.Browser;
import common.DriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;

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
		MainPage mainPage = new MainPage(driver);
		mainPage.open();
		System.out.println("driver.getTitle()  " + driver.getTitle());
		System.out.println("https://www.goodreads.com/ opened");
		Thread.sleep(3000);

}
}