package com.QA.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	public static Properties  property;
	public static WebDriver driver;
	public static ChromeOptions option;
	
	public TestBase()
	{
		System.out.println(System.getProperty("user.dir"));
		String path = System.getProperty("user.dir")+"\\src\\main\\java\\properties\\config.properties";
		
		//com.QA.properties  ... com > QA > Utility
		System.out.println("file path is : "+path);
		try {
			FileInputStream file = new FileInputStream(path);
			property=new Properties();
			property.load(file);
			}
			
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
		
		public static void initializebrowser(String browsername)
		{
			//browsername= property.getProperty("browser");
			if(browsername.equals("chrome"))
			{
			  WebDriverManager.chromedriver().setup();
			  option= new ChromeOptions();
			  option.addArguments("--disable-notifications");
			  driver=new ChromeDriver();
		    }
			else if(browsername.equals("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
				
			}
			else if(browsername.equals("firefox"))
			{
				
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.get(property.getProperty("OpenMrs"));
			driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
			
		}

	 
	   
	

}
