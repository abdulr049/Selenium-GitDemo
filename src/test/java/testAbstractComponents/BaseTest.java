package testAbstractComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import pageObjects.LoginPage;

public class BaseTest {

	protected WebDriver driver;
	protected LoginPage loginPage;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();

		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");

		prop.load(fis);

		String browser = prop.getProperty("browser");

		if (browser.equalsIgnoreCase("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("FireFox")) {
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Edge")) {
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {

		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goToLandingPage();

		return loginPage;
	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { "abdul.rahaman12786@gmail.com", "Abdul@123" } };
	}

	@DataProvider
	public Object[][] getDataFromMap() {

		HashMap<String, String> map = new HashMap<String,String>();
		map.put("email", "abdul.rahaman12786@gmail.com");
		map.put("password", "Abdul@123");

		return new Object[][] { { map } };
	}

	public static String getScreenshot(WebDriver driver, String testName) throws IOException {

		String timestamp = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date());

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "//reports//screenshot//" + testName + "_" + timestamp + ".png";

		File destination = new File(path);

		FileUtils.copyFile(source, destination);

		return path;
	}
}