package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class SubmitOrderPage extends AbstractComponents{
	
	WebDriver driver;
	public SubmitOrderPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css=".action__submit")
	WebElement submitOrderButton;
	
	By waitForSubmitOrdersAppear=By.cssSelector(".hero-primary");
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	//String confirmationMessage1 = driver.findElement(By.cssSelector(".hero-primary")).getText();
	
	public void goToSubmitOrderPage()
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitOrderButton);
		waitForElementToAppear(waitForSubmitOrdersAppear);
		
	}
	
	public void validateOrderConfirmationMessage()
	{
		String actualMessage=confirmationMessage.getText();
		System.out.println(actualMessage);
		Assert.assertEquals("THANKYOU FOR THE ORDER.", actualMessage);
	}
	
}
