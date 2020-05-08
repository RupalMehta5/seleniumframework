package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPOM {
	private WebDriver driver; 
	
	public LoginPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(partialLinkText = "LOG IN")
	private WebElement Loginbut;
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	
	@FindBy(className="display-name")
	private WebElement user;
	
	@FindBy(xpath="//li[@id ='wp-admin-bar-user-info']//a[@class='ab-item']//span")
	private WebElement admin1;
	
	@FindBy(xpath="//a[contains(text(),'Edit My Profile')]")
	private WebElement editProfile;
	
	public void Loginbutton ()
	{
		this.Loginbut.click();
	}
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	public void userclick() throws InterruptedException {
		Actions act =new Actions(driver);
		act.moveToElement(user).perform();
		Thread.sleep(6000);
		
		//Match the links displayed under admin
		
		String expected = "admin";
		String actual =admin1.getText();
		System.out.println(actual);
		if (actual.equalsIgnoreCase(expected))
		{
			System.out.println("First link matched");
		}
		
		String editexp = "Edit My Profile";
		String editact =editProfile.getText();
		System.out.println(actual);
		if (editact.equalsIgnoreCase(editexp))
		{
			System.out.println("Second link matched");
		}
	}
	
	
}
