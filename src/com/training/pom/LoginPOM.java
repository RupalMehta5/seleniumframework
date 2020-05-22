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

	@FindBy(xpath = "//div[@class='wp-menu-name' and contains(text(),'Users')]")
	private WebElement users;

	@FindBy(xpath = "//*[@class='wp-first-item current' and contains(text(),'All Users')]")
	private WebElement allUsers;

	@FindBy(xpath = "//input[@type='search']")
	private WebElement searchBox;

	@FindBy(xpath = "//input[@value='Search Users']")
	private WebElement searchUsers;

	@FindBy(css = "td.name.column-name")
	private WebElement firstUser;

	@FindBy(xpath = "//input[@type='checkbox' and @name='users[]']")
	private WebElement checkBox;

	@FindBy(xpath = "//select[@id='new_role']")
	private WebElement roleDropdown;

	@FindBy(xpath = "//input[@value='Change']")
	private WebElement changebutton;

	@FindBy(xpath = "//div[@id='message']")
	private WebElement rolemessage;

	@FindBy(xpath = "//a[contains(text(),'New Launch ')]")
	private WebElement newLaunch;

	@FindBy(xpath = "//img[@class= 'wp-post-image']")
	private WebElement plots;

	@FindBy(xpath = "//input[@name ='your-name']")
	private WebElement yourName;

	@FindBy(xpath = "//input[@name ='your-email']")
	private WebElement yourEmail;

	@FindBy(xpath = "//input[@name ='your-subject']")
	private WebElement yourSubject;

	@FindBy(xpath = "//textarea[@name ='your-message']")
	private WebElement yourMessage;

	@FindBy(xpath = "//input[@value='Send']")
	private WebElement sendButton;

	@FindBy(xpath = "//div[@role='alert' and @style='display: block;']")
	private WebElement errmsg;

	@FindBy(xpath = "//div[contains(text(),'Posts')]")
	private WebElement posts;

	@FindBy(xpath = "//a[contains(text(),'All Posts')]")
	private WebElement allPosts;

	@FindBy(xpath = "//a[contains(text(),'Add New')]")
	private WebElement addNewPost;

	@FindBy(xpath = "//a[contains(text(),'Categories')]")
	private WebElement addCategory;

	@FindBy(css = "input#tag-name")
	private WebElement catName;

	@FindBy(css = "input#tag-slug")
	private WebElement catSlug;

	@FindBy(css = "select#parent")
	private WebElement patCat;

	@FindBy(css = "textarea#tag-description")
	private WebElement catDesc;

	@FindBy(xpath = "//input[@value='Add New Category']")
	private WebElement addNewCat;

	@FindBy(xpath = "//input[@value='Search Categories']")
	private WebElement searchCategory;

	@FindBy(xpath = "//input[@value='Search Posts']")
	private WebElement searchPosts;

	@FindBy(xpath = "//a[@class='row-title']")
	private WebElement actName;

	@FindBy(css = "input#title")
	private WebElement title;

	@FindBy(css = "textarea#content")
	private WebElement body;

	@FindBy(css = "input#publish")
	private WebElement publishbut;

	@FindBy(css = "a.row-title")
	private WebElement displayProperty;

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

	public void changeUserRole() {
		wait(users);
		this.users.click();
		this.allUsers.click();

	}

	public void searchBox(String checkbox, String role) {
		wait(searchBox);
		this.searchBox.sendKeys(checkbox);
		this.searchUsers.click();
		String user1 = this.firstUser.getText();
		if (user1.equalsIgnoreCase(checkbox)) {
			this.checkBox.click();
			Assert.assertTrue(this.checkBox.isSelected());
			Select sec = new Select(roleDropdown);
			sec.selectByVisibleText(role);
			this.changebutton.click();
			String message = this.rolemessage.getText();
			String actual1 = "Changed roles.";
			if (message.contains(actual1)) {
				System.out.println("Changed roles message displayed");
			}

		}
	}

	public void newLaunchData() {
		Actions act1 = new Actions(driver);
		act1.moveToElement(newLaunch).perform();
		wait(plots);
		this.plots.click();

	}

	public void propenquiry(String Name, String Email, String Subject, String Message) {
		this.yourName.sendKeys(Name);
		this.yourEmail.sendKeys(Email);
		this.yourSubject.sendKeys(Subject);
		this.yourMessage.sendKeys(Message);
		this.sendButton.click();
		String err = this.errmsg.getText();
		if (err.contains("error")) {
			System.out.println("Error message is displayed");
		}

	}

	public void postCategory(String Name, String Slug, String PCategory, String Description, String Title, String Body)
			throws AWTException {
		this.posts.click();
		this.addCategory.click();
		this.catName.sendKeys(Name);
		this.catSlug.sendKeys(Slug);
		Select sec = new Select(patCat);
		sec.selectByVisibleText(PCategory);
		this.catDesc.sendKeys(Description);
		this.addNewCat.click();
		driver.navigate().refresh();
		this.searchBox.sendKeys(Name);
		this.searchCategory.click();
		String act = this.actName.getText();
		if (Name.equalsIgnoreCase(act)) {
			System.out.println("Added category displayed in category section");
		}

		this.addNewPost.click();
		this.title.sendKeys(Title);
		this.body.sendKeys(Body);
		this.publishbut.click();
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
				this.posts.click();
				this.allPosts.click();
				this.searchBox.sendKeys(Title);
				String disprop = this.displayProperty.getText();
				if (Title.equalsIgnoreCase(disprop)) {
					System.out.println("added property details are displayed");
				}
			}
		}

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
