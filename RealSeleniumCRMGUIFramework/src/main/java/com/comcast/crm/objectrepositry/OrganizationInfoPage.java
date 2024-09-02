package com.comcast.crm.objectrepositry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver=null;
	public OrganizationInfoPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement headerinfo;
	
	@FindBy(id="dtlview_Organization Name")
	private WebElement editorgname;
	
	@FindBy(id="dtlview_Industry")
	private WebElement industrydd;
	
	@FindBy(id="dtlview_Type")
	private WebElement typedd;
	
	@FindBy(id="dtlview_Phone")
	private WebElement editphoneno;
	
	public WebElement getHeaderinfo() {
		return headerinfo;
	}
	
	

	public WebElement getIndustrydd() {
		return industrydd;
	}



	public WebElement getTypedd() {
		return typedd;
	}



	public WebElement getEditorgname() {
		return editorgname;
	}



	public WebElement getEditphoneno() {
		return editphoneno;
	}
	
	
	
	
	
	
}
