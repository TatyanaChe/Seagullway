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

public class ResultSearchPage<type> extends PageObject {

	@FindBy(xpath = "//table[@class='tableList']")
	private WebElement tableList;

	@FindBy(xpath = "//table[@class='tableList']/tr/td/div")
	private WebElement tableItemList;

	@FindBy(xpath = "*//a[@class='bookTitle']")
	public List<WebElement> bookList;

	@FindBy(xpath = "//tr[@itemtype='http://schema.org/Book']")
	public List<WebElement> searchList;

	@FindBy(xpath = "//button[@class='wtrToRead']")
	private WebElement buttonWantToRead;

	public ResultSearchPage(WebDriver driver) {
		super(driver);
	}

	public void markFirstNumberResultsAsWantToRead(Integer qty) {
		List<WebElement> itemscopes = new ArrayList<WebElement>();
		itemscopes = driver.findElements(By.xpath("//table[@class='tableList']/tbody/tr"));
		ArrayList<String> bookHrefList = new ArrayList<String>();
		Iterator<WebElement> it = itemscopes.iterator();
		int count = 0;
		while (it.hasNext()) {
			System.out.println("count: ds " + count);
			if (count >= qty ) {
				System.out.println("count: breaking " + count);
				break;
			}
			count++;

			WebElement el = it.next();
			String bookHref = el.findElement(By.xpath(".//td[2]/a")).getText();
			bookHrefList.add(bookHref);
			System.out.println("bookHrefList: " + bookHrefList);
		}
// Mark books
		for (String bookHref : bookHrefList) {
			System.out.println("bookHref: " + bookHref);
			driver.get(bookHref);

		}

	}
}
