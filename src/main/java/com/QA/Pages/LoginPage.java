package com.QA.Pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	WebDriver driver;
	
	@FindBy (name="username")
	private WebElement user_name;
	
	@FindBy(id="password")
	private WebElement pwd;
	
	@FindBy(xpath="//ul[@id='sessionLocation']//li")
	private List<WebElement> locations;
	
	@FindBy(id="loginButton")
	private WebElement login_button;
	
	@FindBy(xpath="//div[@class='logo']/a/img")
	private WebElement logo;
	
	public LoginPage(WebDriver rdriver)
	{
		driver = rdriver;
		PageFactory.initElements(driver,this);
	}
	
	public String username(String name)
	
	{
		user_name.sendKeys(name);
		return name;
	}
 
	public void password(String pass)
	{
		pwd.sendKeys(pass);
	}
	
	public void SelectOption(String opt)
	{
		for(WebElement wb :locations)
		{
			if(wb.getText().equals(opt))
			{
				wb.click();
			}
		}
	}
	
	public String GetTile()
	{
	  return driver.getTitle();
		
	}
	
	public int checkoption_count()
	{
		return locations.size();
	}
	
	
	public ArrayList<Object> check_optiondata()
	{
		ArrayList<Object> arr = new ArrayList<>();
		
		for(WebElement wb :locations)
		{
				arr.add(wb.getText());
			
		}
		
		return arr ;
	}
	
	public void Login_button()
	{
		login_button.click();
	}
	
	
	public HomePage NavigateTohomepage(String name, String pass, String opt)
	{
		user_name.sendKeys(name);
		pwd.sendKeys(pass);
		for(WebElement wb :locations)
		{
			if(wb.getText().equals(opt))
			{
				wb.click();
			}
		}
		login_button.click();
		
		return new HomePage();
	}
}
