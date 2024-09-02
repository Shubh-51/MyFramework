package com.comcast.crm.objectrepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class HomePage {
	WebDriver driver=null;
	WebDriverUtility wlib=new WebDriverUtility();
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='Organizations']")
	private WebElement orglink;
	
	@FindBy(xpath="//img[@style='padding: 0px;padding-left:5px']")
	private WebElement adminlink;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement singout;
	
	@FindBy(xpath="//a[text()='Contacts']")
	private WebElement contactlink;
	
	

	public WebElement getAdminlink() {
		return adminlink;
	}

	public WebElement getSingoutlink() {
		return singout;
	}

	public WebElement getOrglink() {
		return orglink;
	}

	public WebElement getContactlink() {
		return contactlink;
	}
	
	public void singOutToApplication() {
		wlib.MouseHoverToElement(driver, adminlink);
		singout.click();	
	}
	
	
}
