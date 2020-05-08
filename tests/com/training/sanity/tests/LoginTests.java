package com.training.sanity.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	
	/**
	 * TC #13 ->To Verify whether application allows the admin to change the profile details
	 */
	@Test
	public void validprofiledetails() throws InterruptedException {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		loginPOM.userclick();
		loginPOM.linksverify();
		loginPOM.profileclick();
		loginPOM.sendlastname("Manzoor");
		loginPOM.sendphone("9876543210");
		screenShot.captureScreenShot("First");
	}
	
	/**
	 * TC #15 ->To Verify whether application allows the admin to logout from the application
	 */
	
	@Test
	public void logoutCheck()   {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		loginPOM.userclick();
		loginPOM.linksverify();
		loginPOM.logout();
		screenShot.captureScreenShot("Second");
	}
	
	/**
	 * TC #15 ->TO Verify whether application allows admin to filter properties details based on the search criteria
	 */
	@Test
	public void filterProperties() throws InterruptedException   {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		loginPOM.clickProperties();
		loginPOM.clickAllProperties();
		loginPOM.allDateslist();
		loginPOM.clickFilterbut();
		loginPOM.postDetails();
		screenShot.captureScreenShot("Third");
	}
	
}
