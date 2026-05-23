package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CardPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CardPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cardButton;
	
	By waitForCardLoad=By.cssSelector(".cartSection h3");
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cardProducts;
	
	By waitForcheckout=By.cssSelector(".subtotal button");
	
	
	public void goTocardPage()
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cardButton);
		waitForElementToAppear(waitForCardLoad);
	}
	
	public CheckOutPage cardProducts(String productName)
	{
		boolean match = cardProducts.stream()
				.anyMatch(cardProduct -> cardProduct.getText().trim().equalsIgnoreCase(productName));
		// Assert.assertTrue(match);
		System.out.println(match);
		waitForElementToAppear(waitForcheckout);
		
		CheckOutPage checkOutPage = new CheckOutPage(driver);
		return checkOutPage;
	}

}
