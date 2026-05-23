package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".subtotal button")
	WebElement checkoutButton;
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryInput;
	
	By waitTillCountryAppear=By.cssSelector(".ta-results");
	
	@FindBy(css=".ta-item")
	List<WebElement> countryNames;

		
	public void goToCheckOutPage()
	{
	
		checkoutButton.click();
	}
	
	public SubmitOrderPage selectCountry(String countryName)
	{
		countryInput.sendKeys(countryName);

		waitForElementToAppear(waitTillCountryAppear);

		for (WebElement country : countryNames) {
			if (country.getText().trim().equalsIgnoreCase(countryName)) {
				country.click();
				break;
			}
		}
		
		SubmitOrderPage submitOrderPage = new SubmitOrderPage(driver);
		return submitOrderPage;
	}
	
}
