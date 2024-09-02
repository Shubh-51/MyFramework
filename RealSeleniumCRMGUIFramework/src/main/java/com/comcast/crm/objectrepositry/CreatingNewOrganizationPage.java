package com.comcast.crm.objectrepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class CreatingNewOrganizationPage {
	WebDriver driver=null;
	WebDriverUtility wlib=new WebDriverUtility();
	
	public CreatingNewOrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement editorgname;
	
	@FindBy(name="industry")
	private WebElement industrydd;
	
	@FindBy(name="accounttype")
	private WebElement typedd;
	
	@FindBy(name="phone")
	private WebElement editphoneno;
	
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement savebtn;

	public WebElement getEditorgname() {
		return editorgname;
	}
	
	

	public WebElement getIndustrydd() {
		return industrydd;
	}



	public WebElement getTypedd() {
		return typedd;
	}



	public WebElement getSavebtn() {
		return savebtn;
	}
	
	
	
	public WebElement getPhoneno() {
		return editphoneno;
	}



	public void createNewOrg(String orgname) {
		editorgname.sendKeys(orgname);
		savebtn.click();
	}
	
	public void createNewOrg(String orgname,String indusrty,String type) {
		editorgname.sendKeys(orgname);
		wlib.selectFromDropDown(getIndustrydd(), indusrty);
		wlib.selectFromDropDown(getTypedd(), type);
		savebtn.click();
	}
	
	public void createNewOrg(String orgname,String phoneno) {
		editorgname.sendKeys(orgname);
		editphoneno.sendKeys(phoneno);
		savebtn.click();
	}
	
}
