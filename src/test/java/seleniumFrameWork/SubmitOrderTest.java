package seleniumFrameWork;

import java.util.HashMap;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObjects.CardPage;
import pageObjects.CheckOutPage;
import pageObjects.ProductCatalogue;
import pageObjects.SubmitOrderPage;
import testAbstractComponents.BaseTest;

@Listeners(testAbstractComponents.Listeners.class)

public class SubmitOrderTest extends BaseTest {

	@Test(dataProvider = "getDataFromMap", priority = -1)
	public void submitOrderTest(HashMap<String, String> input) throws InterruptedException {

		// test = extent.createTest("Submit Order Test");

		String productName = "ZARA COAT 3";

		// Login
		ProductCatalogue productCatalogue = loginPage.login(input.get("email"), input.get("password"));

		// Product Catalogue
		CardPage cardPage = productCatalogue.productSearchByName(productName);

		// Cart Page
		cardPage.goTocardPage();
		CheckOutPage checkOutPage = cardPage.cardProducts(productName);

		Thread.sleep(2000);

		// Checkout Page
		checkOutPage.goToCheckOutPage();
		SubmitOrderPage submitOrderPage = checkOutPage.selectCountry("India");

		// Submit Order Page
		submitOrderPage.goToSubmitOrderPage();
		submitOrderPage.validateOrderConfirmationMessage();
		// test.pass("Order Submitted Successfully");
		

	}
	@Test
	public void jenkinsHookDemo()
	{
		System.out.println("This is jenkins Hook Demo ");
	}
	
	@Test
	public void jenkinsHookDemo2()
	{
		System.out.println("This is jenkins Hook Demo ");
	}
	
}