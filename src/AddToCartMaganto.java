import static org.testng.Assert.assertEquals;

import java.awt.Container;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import dev.failsafe.internal.util.Assert;

public class AddToCartMaganto {

	WebDriver driver = new ChromeDriver();

	String Url = "https://magento.softwaretestingboard.com/";

	Random rand = new Random();
	String Pass = "ABC@123!";

	@BeforeTest
	public void BeforTest() {

		driver.manage().window().maximize();

	}

	@Test(invocationCount = 1, priority = 1)
	public void AddOneToCart() throws InterruptedException {
		driver.get(Url);

		WebElement ListOfItem = driver.findElement(By.className("product-items"));
		List<WebElement> ProductItem = ListOfItem.findElements(By.tagName("li"));
		int randomitem = rand.nextInt(ProductItem.size());

		ProductItem.get(randomitem).click();
		if (driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {

			WebElement AddToCart = driver.findElement(By.id("product-addtocart-button"));
			AddToCart.click();
		} else {

			WebElement SizeItem = driver
					.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
			List<WebElement> SelectSize = SizeItem.findElements(By.tagName("div"));
			int RandomSize = rand.nextInt(SelectSize.size());
			SelectSize.get(RandomSize).click();

			WebElement ColorItem = driver
					.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
			List<WebElement> ListOfColors = ColorItem.findElements(By.tagName("div"));
			int RandomColors = rand.nextInt(ListOfColors.size());
			ListOfColors.get(RandomColors).click();
			WebElement AddToCart = driver.findElement(By.id("product-addtocart-button"));
			AddToCart.click();
			Thread.sleep(2000);

		}

	}

	@Test(priority = 2)
	public void CheackOutTest() throws InterruptedException {

		String CheackOutProccec = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(CheackOutProccec);
		Thread.sleep(2000);
		WebElement ProccedToCheckout = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
		ProccedToCheckout.click();
	}

	@Test(priority = 3)
	public void ShippingAddress() throws InterruptedException {
		String ExpectedResult ="Thank you for registering with Main Website Store.";

		Thread.sleep(2000);
		WebElement Email = driver.findElement(By.id("customer-email"));
		WebElement FirstNAME = driver.findElement(By.name("firstname"));
		WebElement LastName = driver.findElement(By.name("lastname"));
		WebElement street = driver.findElement(By.name("street[0]"));
		WebElement City = driver.findElement(By.name("city"));
		WebElement State = driver.findElement(By.name("region_id"));

		WebElement PostCode = driver.findElement(By.name("postcode"));
		WebElement country = driver.findElement(By.name("country_id"));
		WebElement PhoneNumber = driver.findElement(By.name("telephone"));

		Email.sendKeys("sarajadallah97@gmail.com");
		FirstNAME.sendKeys("Sarah");
		LastName.sendKeys("Jadallah");
		street.sendKeys("Alzamakshare");
		City.sendKeys("Amman");
		State.sendKeys("Jordan");

		// country.sendKeys("Jordan");
		PostCode.sendKeys("19933");
		PhoneNumber.sendKeys("0796981345");

		Select select = new Select(country);
		select.selectByValue("JO");

		Thread.sleep(4000);
		WebElement NextBouttont = driver.findElement(By.cssSelector(".button.action.continue.primary"));

		NextBouttont.click();

		Thread.sleep(4000);

		WebElement PlaceOrderBoutton = driver.findElement(By.cssSelector(".action.primary.checkout"));
		PlaceOrderBoutton.click();

		Thread.sleep(4000);

		WebElement CreatAccount = driver.findElement(
				By.xpath("//a[@href='https://magento.softwaretestingboard.com/checkout/account/delegateCreate/']"));

		CreatAccount.click();
		Thread.sleep(4000); 

		WebElement password = driver.findElement(By.id("password"));
		WebElement passwordconfirmation = driver.findElement(By.id("password-confirmation"));
		password.sendKeys(Pass);
		passwordconfirmation.sendKeys(Pass);
		
		WebElement CreateAnAccount = driver.findElement(By.cssSelector(".action.submit.primary"));
		CreateAnAccount.click();
		Thread.sleep(3000); 

		WebElement Succesfulmsg=driver.findElement(By.xpath("//div[@class='message-success success message']"));
		String ActualResult = Succesfulmsg.getText();
		org.testng.Assert.assertEquals(ActualResult, ExpectedResult);
		
	

		
		
		
	}

}
