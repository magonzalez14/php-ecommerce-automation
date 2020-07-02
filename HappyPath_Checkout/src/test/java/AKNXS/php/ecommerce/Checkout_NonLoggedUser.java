package AKNXS.php.ecommerce;
import static org.testng.Assert.assertTrue;

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
import org.testng.annotations.Test;

public class Checkout_NonLoggedUser {
	
	private WebDriver driver;
	By ShopElement = By.cssSelector("a[href='http://itluma.com/product/basic-shirt/']");
	By SuccessMsg = By.xpath("//*[@id=\"content\"]/div/div/div[2]/p");
	
	@BeforeClass
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void NonLoggedUser()
	{
		//Proceed to checkout page
		driver.get("http://itluma.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.linkText("Shop")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(ShopElement));
		
		driver.findElement(By.cssSelector("a[href='?add-to-cart=5241']")).click();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebElement element = driver.findElement(By.cssSelector("a[href='http://itluma.com/checkout/']"));
		((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
		
		//Fill "Billing details" form
		driver.findElement(By.id("billing_first_name")).sendKeys("Alejandro");
		driver.findElement(By.id("billing_last_name")).sendKeys("Vazquez");
		driver.findElement(By.id("billing_address_1")).sendKeys("Rio Tiber 406, Colinas del Rio");
		driver.findElement(By.id("billing_city")).sendKeys("Aguascalientes");
		driver.findElement(By.id("billing_postcode")).sendKeys("20020");
		driver.findElement(By.id("billing_phone")).sendKeys("4491692603");
		driver.findElement(By.id("billing_email")).sendKeys("alexvazquez@gmail.com");
		
		driver.findElement(By.id("place_order")).click();
		
		//Validate success message
		WebDriverWait wait2 = new WebDriverWait(driver, 10);
		wait2.until(ExpectedConditions.presenceOfElementLocated(SuccessMsg));
        System.out.println("This is result message:" + driver.findElement(SuccessMsg).getText());
		assertTrue(driver.findElement(SuccessMsg).isDisplayed(), "The result message is not present");
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
	}


}
