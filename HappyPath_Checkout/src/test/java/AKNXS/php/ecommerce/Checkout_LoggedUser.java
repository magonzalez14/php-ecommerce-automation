package AKNXS.php.ecommerce;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class Checkout_LoggedUser {
	
	private WebDriver driver;
	By ShopElement = By.cssSelector("a[href='http://itluma.com/product/blazzer/']");
	By SuccessMsg = By.xpath("//*[@id=\"content\"]/div/div/div[2]/p");
	
	@BeforeClass
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void LoggedUser()
	{
		//Login as simple user
		driver.get("http://itluma.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("My account")).click();
		driver.findElement(By.id("username")).sendKeys("diegozamora.ita@hotmail.com");
		driver.findElement(By.id("password")).sendKeys("e-commerce_123");
		driver.findElement(By.name("login")).click();
		
		//Proceed to checkout page
		driver.findElement(By.linkText("Shop")).click();
				
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(ShopElement));
				
		driver.findElement(By.cssSelector("a[href='?add-to-cart=5212']")).click();
				
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.cssSelector("a[href='http://itluma.com/checkout/']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		
		//Complete purchase
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(By.id("place_order"))).click();
		
		//Validate success message
		WebDriverWait wait3 = new WebDriverWait(driver, 10);
		wait3.until(ExpectedConditions.presenceOfElementLocated(SuccessMsg));
		System.out.println("This is result message:" + driver.findElement(SuccessMsg).getText());
		assertTrue(driver.findElement(SuccessMsg).isDisplayed(), "The result message is not present");
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
	}


}
