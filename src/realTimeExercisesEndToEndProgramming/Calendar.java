package realTimeExercisesEndToEndProgramming;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class Calendar {
//	NEED TO SOLVE CALENDAR PICKING FUNCTIONALITY
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\csanchezruiz\\OneDrive - IGT PLC\\Desktop\\IGT\\Selenium Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://path2usa.com/travel-companions");
		driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,1290)");
		Thread.sleep(5000);
		//May 22
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='form-field-travel_comp_date']")));
		//wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(".//*[@id='form-field-travel_comp_date']")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.id("form-field-travel_comp_date")));
		
		
		
		//String datacal = driver.findElement(By.xpath(".//*[@id='form-field-travel_comp_date']")).getText();
		driver.findElement(By.xpath(".//*[@id='form-field-travel_from']")).click();

		//System.out.println(datacal);

		//driver.findElement(By.xpath(".//*[@id='form-field-travel_comp_airline']")).click();
		//driver.findElement(By.id("form-field-travel_comp_date")).click();
		
		
		while(!driver.findElement(By.cssSelector("[class='datepicker-days'] [class='datepicker-switch']")).getText().contains("June"))
		{
			driver.findElement(By.cssSelector("[class='datepicker-days'] th[class='next']")).click();
		}
		//Grab the common attribute, put it in a list and iterate
		List <WebElement> dates = driver.findElements(By.className("flatpickr-day"));
		
		for(int i=0;i<dates.size();i++)
		{
			String day = driver.findElements(By.className("flatpickr-day")).get(i).getText();
			if(day.equalsIgnoreCase("22"))
			{
				driver.findElements(By.className("flatpickr-day")).get(i).click();
				break;
			}
		}
		
	}

}
