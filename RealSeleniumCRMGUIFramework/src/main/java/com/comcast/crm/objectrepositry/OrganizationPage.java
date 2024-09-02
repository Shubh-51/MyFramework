package com.comcast.crm.objectrepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdriverutility.WebDriverUtility;

public class OrganizationPage {
	
	WebDriverUtility wlib=new WebDriverUtility();
	WebDriver driver=null;
	
	public OrganizationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search_text")
	private WebElement editsearch;
	
	@FindBy(name="search_field")
	private WebElement searchdd;
	
	@FindBy(name="submit")
	private WebElement searchbtn;
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createorgbtn;
	
	@FindBy(xpath="//span[contains(text(),'No Organization Found !')]")
	private WebElement noorgtext;
	
	@FindBy(xpath="//td[contains(text(),'Showing Records')]")
	private WebElement searchrecord;
	
	

	public WebElement getSearchrecord() {
		return searchrecord;
	}

	public WebElement getCreateorgbtn() {
		return createorgbtn;
	}

	public WebElement getEditsearch() {
		return editsearch;
	}

	public WebElement getSearchdd() {
		return searchdd;
	}

	public WebElement getSearchbtn() {
		return searchbtn;
	}
	
	public WebElement getNoorgtext() {
		return noorgtext;
	}

	public void deleteAlreadyCreatedOrg(String orgname,String visibletext) {
		editsearch.sendKeys(orgname);
		wlib.selectFromDropDown(searchdd, visibletext);
		searchbtn.click();
	}
	
	public void deleteAlreadyCreatedOrgWithEnter(String orgname,String visibletext) {
		wlib.selectFromDropDown(searchdd, visibletext);
		editsearch.sendKeys(orgname);
		
	}
	
	
}
