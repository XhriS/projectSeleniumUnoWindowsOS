package deepDiveIntoFunctionalTestingWithSelenium;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;


public class Base {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\csanchezruiz\\OneDrive - IGT PLC\\Desktop\\IGT\\Selenium Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		//Implicit Wait on all elements
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); DISABLED
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(5));
		String [] itemsNeeded = {"Cucumber", "Brocolli", "Beetroot"};
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		Thread.sleep(3000);
		addItems(driver, itemsNeeded);
		
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		driver.findElement(By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]")).click();
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("input.promoCode")));
		driver.findElement(By.cssSelector("input.promoCode")).sendKeys("rahulshettyacademy");
		driver.findElement(By.cssSelector("button.promoBtn")).click();
		
		//Explicit Wait on particular element
		
		w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("span.promoInfo")));
		System.out.println(driver.findElement(By.cssSelector("span.promoInfo")).getText());
		
	}
	
	public static void addItems(WebDriver driver, String[] itemsNeeded)
	{
		int j=0;
		List <WebElement> products = driver.findElements(By.cssSelector("h4.product-name"));

		for(int i=0;i<products.size();i++)
		{
			String[] name = products.get(i).getText().split("-"); //splits words that are separated by -
			String formattedName = name[0].trim(); //Takes the first word separated by the - and trims blank spaces
			//Format name to get actual vegetable name (no 1 Kg)
			//check whether name you extracted is present in array or not
			//convert array into array list for easy search
			List itemsNeededList = Arrays.asList(itemsNeeded);
			//if(name.contains("Cucumber")) --> For Array
			
			if(itemsNeededList.contains(formattedName))
			{
				j++;
				//Click on add to cart
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
				//Can't use 'break' for multiple array items
				if (j==itemsNeeded.length)
				{
					break;
				}
			}
		}
	}

}
