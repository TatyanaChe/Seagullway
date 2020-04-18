package pages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultSearchPage extends PageObject {

	public ResultSearchPage(WebDriver driver) {
		super(driver);
	}

	public ArrayList<String> listFirstNumberResultsAsWantToRead(Integer qty) {
		List<WebElement> itemscopes = new ArrayList<WebElement>();
		itemscopes = driver.findElements(By.xpath("//table[@class='tableList']/tbody/tr"));
		ArrayList<String> bookHrefTextList = new ArrayList<String>();
		ArrayList<String> bookHrefList = new ArrayList<String>();
		Iterator<WebElement> it = itemscopes.iterator();
		int count = 0;
		while (it.hasNext()) {
			if (count >= qty) {
				break;
			}
			count++;

			WebElement el = it.next();
			String bookHrefText = el.findElement(By.xpath(".//td[2]/a")).getText();
			bookHrefTextList.add(bookHrefText);
			String bookHref = el.findElement(By.xpath(".//td[2]/a")).getAttribute("href");
			bookHrefList.add(bookHref);
			System.out.println("bookHrefTextList: " + bookHrefTextList);
			System.out.println("bookHrefList: " + bookHrefList);
		}
		return bookHrefList;
	}
}
