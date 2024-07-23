package com.QA.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.QA.Base.TestBase;

public class Util extends TestBase {
	
	static String Test_data_path = "C:\\Users\\Vaibhav\\Desktop\\OpenMrs.xlsx";
	
	public static void WaitforElemet(WebElement element)
	{
	WebDriverWait webriverwait = new WebDriverWait(driver,Duration.ofSeconds(15));
	webriverwait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void ElementTobeClickable(WebElement element)
	{
		WebDriverWait webriverwait = new WebDriverWait(driver,Duration.ofSeconds(15));
		webriverwait.until(ExpectedConditions.elementToBeClickable(element));
	}
	 
	public static void WaitforElements(List<WebElement> element)
	{
		WebDriverWait webriverwait = new WebDriverWait(driver,Duration.ofSeconds(15));
		webriverwait.until(ExpectedConditions.visibilityOfAllElements(element));
	}
	
	public static void click(WebElement element)
	{
		element.click();
	}
	
	public static void sendkeys(WebElement element,String value)
	{
		element.sendKeys(value);
	}
	
	public static String TakeScreenShot(String path)
	{
		
		
			TakesScreenshot ts =((TakesScreenshot)driver);
			String BaseScreenshot= ts.getScreenshotAs(OutputType.BASE64); 
			byte[] decodescreenshot=Base64.getDecoder().decode(BaseScreenshot);
			//Thread.sleep(5000);
			File file = new File(path);
			try {
				FileOutputStream fos = new FileOutputStream(file);
				try {
					fos.write(decodescreenshot);
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		return path;
		}
	
	 
  public static Object[][] getTestData(String Sheetname)
   {
	FileInputStream file= null;
	Workbook book = null;
	try {
		file=new FileInputStream(Test_data_path);
		
	} catch (FileNotFoundException e)
	{
	 e.printStackTrace();
	}
	try
    {
		book= WorkbookFactory.create(file);
	} catch (EncryptedDocumentException e) 
	{
		e.printStackTrace();
	} catch (IOException e) 
	{
		e.printStackTrace();
	}
	Sheet sh= book.getSheet(Sheetname);
	Object [][] data = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];
	
	for(int i=0; i<sh.getLastRowNum();i++)// i=0
	{
	  for(int j=0;j<sh.getRow(0).getLastCellNum();j++)	// j=0, 0<3 : T
	  {
		  data[i][j] = sh.getRow(i+1).getCell(j).toString(); // getrow(1).getCell(0)
	  }
	
	}
	return data;
} 
	
  public static ArrayList<Object> ReadArralist(String Sheetname)
  {
	FileInputStream file= null;
	Workbook book = null;
	try {
		file=new FileInputStream(Test_data_path);
		
	} catch (FileNotFoundException e)
	{
	 e.printStackTrace();
	}
	try
   {
		book= WorkbookFactory.create(file);
	} catch (EncryptedDocumentException e) 
	{
		e.printStackTrace();
	} catch (IOException e) 
	{
		e.printStackTrace();
	}
	Sheet sh= book.getSheet(Sheetname);
	
	ArrayList<Object> data = new ArrayList<>();
	
	
	  for(int i=0;i<sh.getLastRowNum();i++)	// j=0, 0<3 : T
	  {
		   data.add(sh.getRow(i+1).getCell(0).toString()); // getrow(1).getCell(0)
	  }
	
	
	return data;
} 
}
