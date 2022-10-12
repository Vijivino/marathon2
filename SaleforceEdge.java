package marathon2;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SaleforceEdge extends BaseClassSalesforce {

	@BeforeTest
	public void setexcelname() {
		excelname="SalesForce";
		sheetname="TC002";
	}


	@Test(dataProvider="fetchdata")

	public void createcustomer(String title,String name) throws InterruptedException {


		//	    04) Click View All
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();	
		Thread.sleep(3000);
		//		05) Type Individuals on the Search box
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("individuals");
		//		06) Click Individuals Link
		driver.findElement(By.xpath("//mark[text()='Individuals']")).click();
		Thread.sleep(3000);
		//		07) Click New
		driver.findElement(By.xpath("//div[text()='New']")).click();
		Thread.sleep(3000);
		//		08) Select Salutation with data (coming from excel)
		driver.findElement(By.xpath("//div[contains(@class,'salutation')]")).click();
		driver.findElement(By.xpath("//a[text()='"+title+"']")).click();
		//		09) Type Last Name 
		driver.findElement(By.xpath("//input[@placeholder='Last Name']")).sendKeys(name);
		Thread.sleep(3000);
		//		10) Click Save
		driver.findElement(By.xpath("(//span[text()='Save'])[2]")).click();
		Thread.sleep(4000);
		//		11) Click on the App Launcher (dots)
		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click(); 
		Thread.sleep(3000);
		//		12) Click View All
		driver.findElement(By.xpath("//button[text()='View All']")).click();	
		Thread.sleep(3000);
		//		13) Type Customers on the Search box
		driver.findElement(By.xpath("(//input[@class='slds-input'])[2]")).sendKeys("customers");
		Thread.sleep(2000);
		//		14) Click Customers Link
		driver.findElement(By.xpath("//mark[text()='Customers']")).click();
		Thread.sleep(3000);
		//		15) Click New
		driver.findElement(By.xpath("//div[text()='New']")).click();
		Thread.sleep(3000);
		//		16) Type the same name provided in step 8 and confirm it appears
		driver.findElement(By.xpath("//input[@role='combobox']")).sendKeys(name);
		String outputname = driver.findElement(By.xpath("//div[@class='primaryLabel slds-truncate slds-lookup__result-text']")).getText();
		// System.out.println(outputname);
		if(outputname.equals(name)) {
			System.out.println("customer Name found -- "+name);
		}else {
			System.out.println("No customer found");
		}





	}
}
