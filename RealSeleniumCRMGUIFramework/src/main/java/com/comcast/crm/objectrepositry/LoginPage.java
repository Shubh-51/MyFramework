package com.comcast.crm.objectrepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class LoginPage {
	WebDriverUtility wlib=new WebDriverUtility();
	WebDriver driver=null;
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="user_name")
	private WebElement editusername;
	
	@FindBy(name="user_password")
	private WebElement editpassword;
	
	@FindBy(id="submitButton")
	private WebElement loginbtn;
	public WebElement getEditusername() {
		return editusername;
	}

	public WebElement getEditpassword() {
		return editpassword;
	}

	public WebElement getLoginbtn() {
		return loginbtn;
	}
	
	public void loginToApplication(String url,String username,String password) {
		driver.get(url);
		wlib.getWindowMaximize(driver);
		wlib.WaitForPageToLoad(driver);
		editusername.sendKeys(username);
		editpassword.sendKeys(password);
		loginbtn.click();
	}
	
	

}
