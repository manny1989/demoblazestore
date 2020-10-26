package test;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import com.google.common.base.Splitter;
import commonFunctions.CommonMethods;
import commonFunctions.Util;

public class LaunchTest {

	@Test
	public void test() throws InterruptedException{
		WebDriver driver = CommonMethods.launchBrowser();
		driver.get(Util.getConfigData("url"));
		driver.manage().window().maximize();	

		//Navigate to "Laptop"
		Thread.sleep(2000);
		WebElement laptopelement = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]"));
		laptopelement.click();

		//"Sony vaio i5"
		Thread.sleep(3000);
		laptopelement = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[1]/div/a/img"));
		laptopelement.click();

		Thread.sleep(2000);
		//"Sony vaio i5" and click on "Add to cart".
		laptopelement = driver.findElement(By.xpath("//*[@id=\"tbodyid\"]/div[2]/div/a"));
		laptopelement.click();

		//Accept pop up confirmation
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		//Goto Home page
		Thread.sleep(2000);
		laptopelement = driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[1]/a"));
		laptopelement.click();

		//Navigate to "Laptop"
		Thread.sleep(2000);
		laptopelement = driver.findElement(By.xpath("/html/body/div[5]/div/div[1]/div/a[3]"));
		laptopelement.click();

		//"Dell i7 8gb"
		Thread.sleep(2000);
		laptopelement = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div/div[4]/div/a/img"));
		laptopelement.click();

		//"Dell i7 8gb" and click on "Add to cart".
		Thread.sleep(2000);
		laptopelement = driver.findElement(By.xpath("/html/body/div[5]/div/div[2]/div[2]/div/a"));
		laptopelement.click();

		//Accept pop up confirmation
		Thread.sleep(3000);
		driver.switchTo().alert().accept();

		//Navigate to "Cart" → Delete "Dell i7 8gb" from cart.
		Thread.sleep(2000);
		laptopelement = driver.findElement(By.xpath("/html/body/nav/div/div/ul/li[4]/a"));
		laptopelement.click();

		Thread.sleep(5000);
		for(int i=1; i<5;i++)
		{
			String itemName = driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr["+i+"]/td[2]")).getText();
			if(itemName.equalsIgnoreCase("Dell i7 8gb"))
			{
				driver.findElement(By.xpath("/html/body/div[6]/div/div[1]/div/table/tbody/tr["+i+"]/td[4]/a")).click();
				break;
			}
		}

		//Click on "Place order"
		Thread.sleep(2000);
		laptopelement = driver.findElement(By.xpath("/html/body/div[6]/div/div[2]/button"));
		laptopelement.click();

		//Fill in all web form fields.
		Thread.sleep(1000);
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[1]/input")).sendKeys("Munish Kumar");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[2]/input")).sendKeys("India");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[3]/input")).sendKeys("Gurgoan");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[4]/input")).sendKeys("democreditcard");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[5]/input")).sendKeys("05");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/form/div[6]/input")).sendKeys("2025");
		driver.findElement(By.xpath("/html/body/div[3]/div/div/div[3]/button[2]")).click();

		Thread.sleep(1000);
		String purchaseinfo = driver.findElement(By.xpath("/html/body/div[10]/p")).getText();
		System.out.println(purchaseinfo);

		Map<String, String> map = splitToMap(purchaseinfo);
		System.out.println("Purchase ID: "+map.get("Id"));
		System.out.println("Amount: "+map.get("Amount"));
		assertEquals(map.get("Amount").trim(), "790 USD");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[10]/div[7]/div/button")).click();		 
	}

	private Map<String, String> splitToMap(String in) {
		return Splitter.on("\n").withKeyValueSeparator(":").split(in);
	}
}
