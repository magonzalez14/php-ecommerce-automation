package AKNXS.php.ecommerce;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MyAccount_Login {
	
	private WebDriver driver;
	By MyAccountDashboard = By.xpath("//nav[@class='woocommerce-MyAccount-navigation']");
	
	@BeforeClass
	public void OpenBrowser()
	{
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void Login()
	{
		//Login as simple user
		driver.get("http://itluma.com/");
		driver.findElement(By.linkText("My account")).click();
		driver.findElement(By.id("username")).sendKeys("diegozamora.ita@hotmail.com");
		driver.findElement(By.id("password")).sendKeys("e-commerce_123");
		driver.findElement(By.name("login")).click();
		
		//Validate if simple user login successfully
		System.out.println("You are logged successfully with next available menu:\n" + driver.findElement(MyAccountDashboard).getText());
		assertTrue(driver.findElement(MyAccountDashboard).isDisplayed(), "The client dashboard is not present");
		
	}
	
	@AfterClass
	public void CloseBrowser()
	{
		driver.close();
	}


}
