package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CommonActions {

	public static void fillForm(WebDriver driver) {
		WebElement name = driver.findElement(By.id("user_first_name"));
		name.clear();
		name.sendKeys("Tanya");
		WebElement email = driver.findElement(By.id("user_email"));
		email.clear();
		email.sendKeys("test16042020@mailinator.com");
	}

}
