package com.training.sanity.tests;

import java.awt.AWTException;
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
import com.training.pom.LoginPOM2;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTests2 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM2 loginPOM;
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
		loginPOM = new LoginPOM2(driver);
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
	 * TC #51 ->To verify whether application displays property details in all
	 * properties upon clicking Restore link of selected property details in trash
	 */
	@Test(priority=1)
	public void validprofiledetails() throws InterruptedException {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		loginPOM.clickProperties();
		loginPOM.clickAllProperties();
		loginPOM.trash();
		loginPOM.restorelink();
		screenShot.captureScreenShot("First");
	}

	/**
	 * TC #52 ->To verify whether application allows admin to delete post
	 * permanently
	 * 
	 * @throws InterruptedException
	 */
	@Test(priority=2)
	public void deletepost() throws InterruptedException {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		loginPOM.clickProperties();
		loginPOM.clickAllProperties();
		loginPOM.moveproperty();
		screenShot.captureScreenShot("Second");
	}

	/**
	 * TC #53 ->To verify whether application displays property details on home
	 * screen upon clicking Restore link of selected property details in trash
	 * 
	 */
	@Test(priority=0)
	public void homeScreen() throws InterruptedException, AWTException {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		loginPOM.clickProperties();
		loginPOM.clickAllProperties();
		loginPOM.trash();
		loginPOM.restorehome();
		screenShot.captureScreenShot("Third");
	}

}
