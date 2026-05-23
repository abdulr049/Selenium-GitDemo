package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {

	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	public void waitForElementToAppear(By findLocator) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findLocator));
	}
	
	public void waitForElementToDisappear(By findLocator) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(findLocator));
	}
}