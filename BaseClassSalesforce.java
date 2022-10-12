package marathon2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClassSalesforce {

	public RemoteWebDriver driver;
	String browser;
	public String excelname,sheetname;
	@Parameters({"browser","url","username","password"})
	@BeforeMethod
	public void preconditions(String browser,String url,String username,String password) throws InterruptedException {

		if(browser.equalsIgnoreCase("chrome")) 
		{
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt=new ChromeOptions();
			opt.addArguments("--disable-notifications");
			driver=new ChromeDriver(opt);
			driver.get(url);
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("edge")) 
		{
			WebDriverManager.edgedriver().setup();
			EdgeOptions opt=new EdgeOptions();
			opt.addArguments("--disable-notifications");
			driver=new EdgeDriver(opt);
			driver.get(url);
			driver.manage().window().maximize();
		}
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();
		Thread.sleep(8000);
	}

	@AfterMethod
	public void postconditions() {
		driver.close();
	}

	@DataProvider(name="fetchdata")
	public String[][] fetchdata() throws IOException {

		return readexceldata(excelname,sheetname);

	}


	public String[][] readexceldata(String excelname,String sheetname) throws IOException{
		XSSFWorkbook book=new XSSFWorkbook("./data/"+excelname+".xlsx");
		XSSFSheet sheet = book.getSheet(sheetname);
		int rowNum = sheet.getLastRowNum();
		short cellNum = sheet.getRow(0).getLastCellNum();
		String[][] data=new String[rowNum][cellNum];
		for(int i=1;i<=rowNum;i++) {
			XSSFRow row = sheet.getRow(i);
			for(int j=0;j<cellNum;j++) {
				String stringCellValue = row.getCell(j).getStringCellValue();
				data[i-1][j]=stringCellValue;

			}
		}
		book.close();
		return data;

	}





}
