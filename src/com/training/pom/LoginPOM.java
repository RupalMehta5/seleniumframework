package com.training.pom;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

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

	@FindBy(css = "li.trash")
	private WebElement trash;

	@FindBy(css = "input.button.apply:nth-of-type(2)")
	private WebElement emptyTrash;

	@FindBy(xpath = "//td[@data-colname='Title']/strong[1]")
	private WebElement propname;
	// div[@class='locked-info']/following-sibling::strong[1]
	// *[@class ='row-actions']/span[1]
	@FindBy(css = "span.locked-indicator-icon")
	private WebElement firstProperty;

	/**
	 * Links Edit,QuickEdit, Trash, View
	 */
	@FindBy(css = "span.edit")
	private WebElement editLink;

	@FindBy(css = "a.editinline")
	private WebElement quickeditLink;

	@FindBy(css = "a.submitdelete")
	private WebElement trashLink;

	@FindBy(css = "span.view")
	private WebElement PreviewLink;

	@FindBy(css = "span.untrash")
	private WebElement restoreLink;

	@FindBy(css = "a.submitdelete")
	private WebElement DeleteLink;

	@FindBy(xpath = "//div[@id='message']")
	private WebElement postmessage;

	@FindBy(css = "li.all")
	private WebElement allLink;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchBox;

	@FindBy(xpath = "//input[@value='Search Properties']")
	private WebElement searchProperties;

	@FindBy(css = "a.row-title")
	private WebElement dispalyProperty;

	// @FindBy(xpath = "//*[@id='responsive']//descendant::li[5]/style")
	@FindBy(css = ".container>ul>li:nth-of-type(5)")
	private WebElement bloglink;

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
	 * Click properties link
	 * 
	 */

	public void clickProperties() throws InterruptedException {
		wait(properties);
		this.properties.click();

	}

	/**
	 * Click Allproperties link
	 */
	public void clickAllProperties() {
		this.allProperties.click();

	}

	/**
	 * Click Trash link and verify
	 */
	public void trash() {
		this.trash.click();
		if (this.emptyTrash.isDisplayed()) {
			System.out.println("Property details present in trash page");
		}
	}

	/**
	 * Click Restore and check in All link property is displayed
	 */
	public void restorelink() throws InterruptedException {

		Actions act = new Actions(driver);
		act.moveToElement(firstProperty).perform();

		String propertyName = this.propname.getText();
		wait(restoreLink);
		this.restoreLink.click();
		System.out.println(this.postmessage.getText());
		this.allLink.click();
		this.searchBox.sendKeys(propertyName);
		this.searchProperties.click();
		String disprop = this.dispalyProperty.getText();
		if (propertyName.equalsIgnoreCase(disprop)) {
			System.out.println("Restored property displayed in all links");
		}
	}

	/**
	 * 
	 * Click restore and check in new tab property details are added
	 */
	public void restorehome() throws InterruptedException, AWTException {

		Actions act = new Actions(driver);
		act.moveToElement(firstProperty).perform();

		String propertyName3 = this.propname.getText();
		wait(restoreLink);
		this.restoreLink.click();
		System.out.println(this.postmessage.getText());
		Robot robo = new Robot();
		robo.keyPress(KeyEvent.VK_CONTROL);
		robo.keyPress(KeyEvent.VK_T);
		robo.keyRelease(KeyEvent.VK_T);
		robo.keyRelease(KeyEvent.VK_CONTROL);
		String parentWindow = driver.getWindowHandle();

		System.out.println("ID is " + parentWindow);

		Set<String> noofWindow = driver.getWindowHandles();

		System.out.println(noofWindow);

		for (String child : noofWindow) {
			if (!child.equals(parentWindow)) {
				driver.switchTo().window(child);
				driver.get("http://realty-real-estate.upskills.in/wp-admin/");
				this.properties.click();
				this.allLink.click();
				this.searchBox.sendKeys(propertyName3);
				this.searchProperties.click();
				String disprop = this.dispalyProperty.getText();
				if (propertyName3.equalsIgnoreCase(disprop)) {
					System.out.println("Restored property displayed in all links");
				}

			}
		}

	}

	/**
	 * Click on Trash link and delete a property permanently
	 */
	public void moveproperty() {

		Actions act = new Actions(driver);
		act.moveToElement(propname).perform();
		String propertyName1 = this.propname.getText();
		System.out.println(propertyName1);
		wait(editLink);
		System.out.println("links displayed are " + editLink.getText() + quickeditLink.getText() + " "
				+ trashLink.getText() + " " + PreviewLink.getText());

		this.trashLink.click();
		String exp = "1 post moved to the Trash";
		String actual = this.postmessage.getText();
		System.out.println(actual);
		Assert.assertTrue(actual.contains(exp));
		this.trash.click();
		String propertyName2 = this.propname.getText();
		System.out.println(propertyName2);
		if (propertyName1.contains(propertyName2)) {
			System.out.println("Property added in trash is displayed");
		}

		Actions act1 = new Actions(driver);
		act.moveToElement(firstProperty).perform();
		wait(DeleteLink);
		this.DeleteLink.click();
		System.out.println(this.postmessage.getText());

	}

	/**
	 * Explicit wait
	 */
	public void wait(WebElement Element) {
		WebDriverWait wait2 = new WebDriverWait(driver, 20);
		wait2.until(ExpectedConditions.elementToBeClickable(Element));
	}

	public void robo() throws AWTException {

	}
}
