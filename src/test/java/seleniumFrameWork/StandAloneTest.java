package seleniumFrameWork;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";

		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/#/auth/login");
		driver.manage().window().maximize();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		driver.findElement(By.id("userEmail")).sendKeys("abdul.rahaman12786@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Abdul@123");
		driver.findElement(By.id("login")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		List<WebElement> productList = driver.findElements(By.cssSelector(".mb-3"));

		WebElement prod = productList.stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

		// Cart button
		WebElement cartButton = driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"));

		// JS click
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().trim().equalsIgnoreCase(productName));
		// Assert.assertTrue(match);
		System.out.println(match);

		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();

		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		List<WebElement> countryNames = driver.findElements(By.cssSelector(".ta-item"));

		for (WebElement country : countryNames) {
			if (country.getText().trim().equalsIgnoreCase("India")) {
				country.click();
				break;
			}
		}

		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action__submit"));

		((JavascriptExecutor) driver).executeScript("arguments[0].click();", placeOrderButton);

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hero-primary")));
		String confirmationMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirmationMessage);
		Assert.assertEquals("THANKYOU FOR THE ORDER.", confirmationMessage);

	}
}
