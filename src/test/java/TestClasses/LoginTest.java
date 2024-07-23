package TestClasses;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.QA.Base.TestBase;
import com.QA.Pages.LoginPage;
import com.QA.Utility.Util;
import Listener.ListnerManager;


@Listeners(ListnerManager.class)
public class LoginTest extends TestBase
{
	LoginPage login;
	SoftAssert soft;
	
	String SuccessfulLoginsheet= "ValidLogin";
	String Options="OptionsData";
   public LoginTest()
   {
	   super();
   }
   
   @BeforeMethod
   @Parameters("Browsername")
   public void prerequsite(String Browsername)
   {
	   initializebrowser(Browsername);
	   login= new LoginPage(driver);
	   soft= new SoftAssert();
   }
   
   @DataProvider
   public Object[][] SuccessfulLogin_getdata()
   {
	   Object[][] data=Util.getTestData(SuccessfulLoginsheet);
	   return data;
   }
   

   @Test(priority=2, dataProvider="SuccessfulLogin_getdata",enabled=true,groups= {"Regression"})
   public void SuccessfulLogin(String Username, String Password , String option)
   {
	   
	   login.username(Username);
	   login.password(Password);
	   login.SelectOption(option);
	   login.Login_button();
	   String ActualTitle=login.GetTile();
	   String ExpectedTitle="Home";
	   System.out.println(ActualTitle);
	   Assert.assertEquals(ActualTitle,ExpectedTitle);
   }
   
   @Test(priority=3,enabled=true,groups= {"Smoke"})
   public void Check_Optioncount_and_data()
   {
	   int Actualcount = login.checkoption_count();
	   System.out.println(Actualcount);
	   int Expectedcount = 5;
	   soft.assertEquals(Actualcount, Expectedcount);
	   ArrayList<Object> Actualoptions = login.check_optiondata();
	   System.out.println(Actualoptions);
	   ArrayList<Object> Expectedoptions= Util.ReadArralist(Options);
	   System.out.println(Expectedoptions);
	   soft.assertEquals( Actualoptions, Expectedoptions);
	   soft.assertAll();
   }
   
   
   
   @Test(enabled=false,priority=1,groups= {"Regression"})
   
   public void Invalid_UsernamePassword(String Username, String Password , String option )
   {
	   login.username("Qwerty");
	   login.password("Qwerty");
	   login.SelectOption("Inpatient Ward");
	   login.Login_button(); 
	   String Title = login.GetTile();
	   System.out.println(Title);
   }
   
   @AfterMethod
   public void teardown()
   {
	   driver.quit();
   }
   
}
