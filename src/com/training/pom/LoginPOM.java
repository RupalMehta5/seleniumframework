package com.training.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPOM {
	private WebDriver driver;

	public LoginPOM(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(partialLinkText = "LOG IN")
	private WebElement Loginbut;

	@FindBy(id = "user_login")
	private WebElement userName;

	@FindBy(id = "user_pass")
	private WebElement password;

	@FindBy(name = "login")
	private WebElement loginBtn;

	@FindBy(className = "display-name")
	private WebElement user;

	// Webelements for 3 links under admin
	@FindBy(xpath = "//*[@id='wp-admin-bar-user-info']//descendant::span")
	private WebElement admin1;

	@FindBy(xpath = "//a[contains(text(),'Edit My Profile')]")
	private WebElement editProfile;

	@FindBy(xpath = "//a[contains(text(),'Log Out')]")
	private WebElement logout;

	@FindBy(xpath = "//h1[contains(text(),'Profile')]")
	private WebElement profile;

	@FindBy(id = "last_name")
	private WebElement lastname;

	@FindBy(id = "phone")
	private WebElement phone;

	@FindBy(id = "submit")
	private WebElement updatePro;

	// @FindBy(className="wp-menu-name")
	@FindBy(xpath = "//div[contains(text(),'Properties')]")
	private WebElement properties;

	@FindBy(xpath = "//a[contains(text(),'All Properties')]")
	private WebElement allProperties;

	@FindBy(id = "filter-by-date")
	private WebElement allDateslist;

	@FindBy(name = "filter_action")
	private WebElement filterbut;

	@FindBy(xpath = "//td[@data-colname='Posted']/strong")
	private WebElement post;

	/**
	 * Enter username/password and hit login button
	 */
	public void Loginbutton() {
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

	/**
	 * Mouse over on admin
	 */
	public void userclick() {
		Actions act = new Actions(driver);
		act.moveToElement(user).perform();
	}

	/**
	 * Verify the 3 links, "admin", "Edit My Profile", "Log Out" is displayed
	 */
	public void linksverify() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement adminlink = wait.until(ExpectedConditions.elementToBeClickable(admin1));
		// WebElement adminlink =
		// wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='wp-admin-bar-user-info']//descendant::span")));
		String expected = "admin";
		String actual = this.admin1.getText();
		// System.out.println(actual);
		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("First link admin matched");
		}
		String editexp = "Edit My Profile";
		String editact = this.editProfile.getText();
		// System.out.println(editact);
		if (editact.equalsIgnoreCase(editexp)) {
			System.out.println("Second link Edit My Profile matched");
		}
		String logexp = "Log Out";
		String logact = this.logout.getText();
		// System.out.println(logact);
		if (logact.equalsIgnoreCase(logexp)) {
			System.out.println("Third link Log Out matched");
		}
	}

	/**
	 * Click My Profile link and verify Profile page displayed
	 */
	public void profileclick() {
		this.editProfile.click();
		String proexp = "Profile";
		String proact = this.profile.getText();
		if (proact.equalsIgnoreCase(proexp)) {
			System.out.println("Profile page is displayed");

		}
	}

	/**
	 *Enter details for profile
	 */
	public void sendlastname(String lastname) {
		this.lastname.clear();
		this.lastname.sendKeys(lastname);
	}

	public void sendphone(String phone) {

		this.phone.clear();
		this.phone.sendKeys(phone);
		this.updatePro.click();
		System.out.println("Profile updated");

	}

	/**
	 * Logout of the page
	 */
	public void logout() {
		this.logout.click();
		System.out.println("Logout link clicked");

	}

	/**
	 * Click properties link
	 * 
	 */
	public void clickProperties() throws InterruptedException {
		WebDriverWait wait1 = new WebDriverWait(driver, 20);
		WebElement proplink = wait1.until(ExpectedConditions.elementToBeClickable(properties));
		// Thread.sleep(5000);
		this.properties.click();

	}

	/**
	 * Click Allproperties link
	 */
	public void clickAllProperties() {
		this.allProperties.click();

	}

	/**
	 * Select value from All dates list box
	 * 
	 */
	public void allDateslist() throws InterruptedException {
		Select sel = new Select(allDateslist);
		sel.selectByVisibleText("September 2019");
		Thread.sleep(1000);

	}

	/**
	 * Click Filter button
	 */
	public void clickFilterbut() {
		this.filterbut.click();

	}

	/**
	 * Check filter criteria match
	 */
	public void postDetails() {
		String postitem = this.post.getText();
		if (postitem.contains("Sep")) {
		System.out.println("All dates filter criteria matched");
		}

	}

}
