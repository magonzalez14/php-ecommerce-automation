package AKNXS.php.ecommerce;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyAccount_Logout {
	
	private WebDriver driver;
	By LoginForm = By.xpath("//div[@class='u-column1 col-1']");
	
	@BeforeClass
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void Logout()
	{
		//Login as simple user
		driver.get("http://itluma.com/");
		driver.findElement(By.linkText("My account")).click();
		driver.findElement(By.id("username")).sendKeys("diegozamora.ita@hotmail.com");
		driver.findElement(By.id("password")).sendKeys("e-commerce_123");
		driver.findElement(By.name("login")).click();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/nav/ul/li[6]/a")).click();
		
		//Validate if simple user can close session successfully
		System.out.println("You are close session successfully, with the following available options:\n" + driver.findElement(LoginForm).getText());
		assertTrue(driver.findElement(LoginForm).isDisplayed(), "Login page is not present");
		
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
	}


}
