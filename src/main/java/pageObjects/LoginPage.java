package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

    WebDriver driver;

    public LoginPage(WebDriver driver) {

        super(driver);
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    String URL = "https://rahulshettyacademy.com/client/#/auth/login";

    @FindBy(id = "userEmail")
    WebElement loginUserEmail;

    @FindBy(id = "userPassword")
    WebElement loginUserPass;

    @FindBy(id = "login")
    WebElement loginbutton;

    By waitForProductsToLoad = By.cssSelector(".mb-3");

    public ProductCatalogue login(String userEmail, String userPassword) {

        loginUserEmail.sendKeys(userEmail);
        loginUserPass.sendKeys(userPassword);
        loginbutton.click();
        waitForElementToAppear(waitForProductsToLoad);
        ProductCatalogue productCatalogue = new ProductCatalogue(driver);
        return productCatalogue;
    }

    public void goToLandingPage() {

        driver.get(URL);
        driver.manage().window().maximize();
    }
}