package miscellaneousTopicsInSeleniumWebDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.net.URLConnection;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class BrokenLinks {
//In order for test to function, java needs to install certificates of website links in cacerts
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws MalformedURLException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\csanchezruiz\\OneDrive - IGT PLC\\Desktop\\IGT\\Selenium Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		SoftAssert s = new SoftAssert();
		List<WebElement> links = driver.findElements(By.cssSelector("li[class='gf-li'] a"));
		
		for(WebElement link : links) //for every element in links
		{
			//Broken URL
			//Step 1 - Get all URLs tied up to the links using Selenium
			//Java methods will call the URLs and get you the status code
			//if status codes are > 400 then the url is not working > url to the link is broken
			String url = link.getAttribute("href");
			
			HttpURLConnection conn = (HttpURLConnection)new URL(url).openConnection();
			conn.setRequestMethod("HEAD");
			conn.connect();
			int respCode = conn.getResponseCode();
			System.out.println(respCode);
			Thread.sleep(1000);
			s.assertTrue(respCode<400, "The link with Text "+link.getText()+ " is broken with code " + respCode);
			
			//if(respCode>400)
			//{
				//System.out.println("The link with Text "+link.getText()+ " is broken with code " + respCode);
				//Assert.assertTrue(false);	
			//}
		}
		
		//Reports all failures and fails script
		s.assertAll();
	
	}

}
