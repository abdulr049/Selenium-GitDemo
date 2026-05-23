package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".mb-3")
	List<WebElement> productList;

	By getProductName = By.cssSelector("b");
	By addToCartBtn = By.cssSelector("button:last-of-type");
	
	By waitForToastContainer= By.cssSelector("#toast-container");
	By waitForAnimating= By.cssSelector(".ng-animating");

	public List<WebElement> productItemList() {
		return productList;
	}

	public CardPage productSearchByName(String productName) {
		WebElement prod = productList.stream()
				.filter(product -> product.findElement(getProductName).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(addToCartBtn).click();
		
		waitForElementToAppear(waitForToastContainer);
		waitForElementToDisappear(waitForAnimating);
		
		CardPage cartPage=new CardPage(driver);
		
		return cartPage;
		
	}

}
