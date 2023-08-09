package seleniumJavaStreamsAutomateSortPaginationFilteringTheWebTables;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class LiveDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\csanchezruiz\\OneDrive - IGT PLC\\Desktop\\IGT\\Selenium Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/offers");
		
		//Click on column
		driver.findElement(By.xpath("//tr/th[1]")).click();
		
		//Capture all webelements into list
		List<WebElement> elementsList =  driver.findElements(By.xpath("//tr/td[1]"));
		
		//Capture test of all webelements into new (original) list
		List<String> originalList = elementsList.stream().map(s->s.getText()).collect(Collectors.toList());
		
		//Sort in the list of step 3 -> sorted list
		List<String> sortedList = originalList.stream().sorted().collect(Collectors.toList());
		
		//Compare original list vs sorted list
		Assert.assertTrue(originalList.equals(sortedList));
		
		List<String> price;
		//Scan the name column with getText -> Beans -> Print the price of the Rice
		do {
		List<WebElement> rows =  driver.findElements(By.xpath("//tr/td[1]"));
		price = rows.stream().filter(s->s.getText().contains("Rice")).
				map(s->getPriceVeggie(s)).collect(Collectors.toList());
		price.forEach(s->System.out.println(s));
		
		if(price.size()<1) {
			driver.findElement(By.cssSelector("[aria-label='Next']")).click();
			}
		}while(price.size()<1);
	}
	
	public static String getPriceVeggie(WebElement s) {
		String priceValue = s.findElement(By.xpath("following-sibling::td[1]")).getText();
		return priceValue;
	}

}
