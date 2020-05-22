package com.training.regression.tests;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginExcelTest {
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
		driver.quit();
	}

	/**
	 * TC74 - To Verify whether application allows admin to change the role of registered user in Users module
	 */
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginDBTest(String checkbox, String role) {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn(); 
		loginPOM.changeUserRole();
		loginPOM.searchBox(checkbox, role);
		screenShot.captureScreenShot(checkbox);

	}
	
	/**
	 * TC75- To verify whether application allows admin to add new category while adding new post & same getting displayed on home screen for user
	 */
	@Test(dataProvider = "excel-inputs1", dataProviderClass = LoginDataProviders.class)
	public void loginDBTest1(String Name, String Slug,String PCategory,String Description,String Title, String Body) throws AWTException {
	//public void loginDBTest1(String Title)  {
		loginPOM.Loginbutton();
		loginPOM.sendUserName("admin");
		loginPOM.sendPassword("admin@123");
		loginPOM.clickLoginBtn();
		loginPOM.postCategory(Name,Slug,PCategory,Description,Title,Body);
		
	}

}