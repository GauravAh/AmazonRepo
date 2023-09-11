package com.Amazon.Tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.Amazon.Base.BaseClass;

public class HomePage extends BaseClass {
	
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFCell cell;
	public XSSFRow row;
	FileInputStream fis;
	
	@Test(priority = 1)
	public void amazonHome() throws Exception {
		getBrowser().get("https://www.amazon.in/");
		getBrowser().manage().window().maximize();
		Thread.sleep(10000);
		WebDriverWait wait = new WebDriverWait(getBrowser(), Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(getBrowser().findElement(By.cssSelector(".nav-fill>div.nav-search-field>input"))));
		
		WebElement searchField = getBrowser().findElement(By.cssSelector(".nav-fill>div.nav-search-field>input"));
		Actions actions = new Actions(getBrowser());
		actions.moveToElement(searchField).click().sendKeys("Iphone13").keyDown(Keys.ENTER).build().perform();
		
		wait.until(ExpectedConditions.visibilityOf(getBrowser().findElement(By.xpath("//*[@data-aid='5153900150202']/div/div[2]/div[1]/div/a"))));
		WebElement iphone = getBrowser().findElement(By.xpath("//*[@data-aid='5153900150202']/div/div[2]/div[1]/div/a"));
		Actions actions1 = new Actions(getBrowser());
		actions1.moveToElement(iphone).click().build().perform();
		Thread.sleep(10000);
		
		JavascriptExecutor js1 = (JavascriptExecutor)getBrowser();
		js1.executeScript("window.scrollBy(0,1200)","");
		
	}
	
	@Test(priority = 2)
	public void writeData() throws Exception {
		Thread.sleep(5000);
		String Brand = getBrowser().findElement(By.xpath("//*[@id='productOverview_feature_div']/div/table/tbody/tr[1]/td[2]/span")).getText();
		System.out.println("------" + Brand);
		
		String ModelName = getBrowser().findElement(By.xpath("//*[@id='productOverview_feature_div']/div/table/tbody/tr[2]/td[2]/span")).getText();
		System.out.println("------" + ModelName);
		
		String NSP = getBrowser().findElement(By.xpath("//*[@id='productOverview_feature_div']/div/table/tbody/tr[3]/td[2]/span")).getText();
		System.out.println("------" + NSP);
		
		String os = getBrowser().findElement(By.xpath("//*[@id='productOverview_feature_div']/div/table/tbody/tr[4]/td[2]/span")).getText();
		System.out.println("------" + os);
		
		String ct = getBrowser().findElement(By.xpath("//*[@id='productOverview_feature_div']/div/table/tbody/tr[5]/td[2]/span")).getText();
		System.out.println("------" + ct);
		
		
		
		Object[][] pData = {
				{"BrandName", Brand},
				{"ModelName",ModelName},
				{"NetworkServiceProvider",NSP},
				{"OperatingSystem",os},
				{"CellularTechnology",ct}
				
		};
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("EmpInfo");
		
		int rows =pData.length;
		System.out.println("Total Rows are.." + rows);
		int cols =pData[0].length;
		System.out.println("Total cols are.." + cols);
		
		for(int i=0;i<rows;i++) {
			row = sheet.createRow(i);
			for(int j=0;j<cols;j++) {
				cell= row.createCell(j);
				Object valuee = pData[i][j];
				
				if(valuee instanceof String)
					cell.setCellValue((String) valuee);
				
			}
		}
		
		String filePath = "./DataExcel/Employee.xlsx";
		FileOutputStream fos = new FileOutputStream(filePath);
		workbook.write(fos);
		fos.close();
	}
	
		

}
