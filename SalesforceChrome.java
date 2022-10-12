package marathon2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SalesforceChrome extends BaseClassSalesforce {
	@BeforeTest
	public void setexcelname() {
		excelname="SalesForce";
		sheetname="TC001";
	}


	@Test(dataProvider="fetchdata")


	public  void createquestion(String question,String description) throws InterruptedException {

		//		01) Launch https://login.salesforce.com/ 
		//			02) Login to Salesforce with your username and password
		//			03) Click on the App Launcher (dots)
		//			04) Click View All
		//			05) Type Content on the Search box
		//			06) Click Content Link
		//			07) Click on Chatter Tab
		//			08) Verify Chatter title on the page
		//			09) Click Question tab
		//			10) Type Question with data (coming from excel)
		//			11) Type Details with data (coming from excel)
		//			12) Click Ask
		//			13) Confirm the question appears
		//			14) Close the browser


		driver.findElement(By.xpath("//div[@class='slds-icon-waffle']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();	
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@class='slds-input']")).sendKeys("content");
		driver.findElement(By.xpath("//mark[text()='Content']")).click();

		Thread.sleep(4000);
		WebElement chatter = driver.findElement(By.xpath("//a[@title='Chatter']"));
		driver.executeScript("arguments[0].click();", chatter);
		Thread.sleep(3000);
		System.out.println(driver.getTitle());
		Thread.sleep(3000);

		driver.findElement(By.xpath("//span[text()='Question']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//textarea[@role='textbox']")).sendKeys(question);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@role='textbox']")).sendKeys(description);

		driver.findElement(By.xpath("//div[@class='bottomBarRight slds-col--bump-left']/button[@type='button']")).click();

		String ques = driver.findElement(By.xpath("//div[@class='cuf-body cuf-questionTitle forceChatterFeedBodyQuestionWithoutAnswer']/span")).getText();
        Thread.sleep(4000);
		System.out.println(ques);
		if(ques.equals(question)) {
			System.out.println("Question is verified -- " +ques);
		}else {
			System.out.println("No question here");

		}

	}



}
