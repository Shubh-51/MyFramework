package com.comcast.crm.main.organizationtest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.excelutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.genric.fileutility.FileUtility;
import com.comcast.crm.objectrepositry.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.LoginPage;

import com.comcast.crm.objectrepositry.OrganizationPage;

public class CreateOrganizationAndDeleteOrganizationTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		 WebDriver driver=null;
		 FileUtility flib=new FileUtility();
		 ExcelUtility elib=new ExcelUtility();
		 JavaUtility jlib=new JavaUtility();
		 WebDriverUtility wlib=new WebDriverUtility();
		
		
		String browser=flib.getDataFromPropertiesFile("browser");
		String url=flib.getDataFromPropertiesFile("url");
		String username=flib.getDataFromPropertiesFile("username");
		String password=flib.getDataFromPropertiesFile("password");
		
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browser.equals("Edge")) {
			driver=new EdgeDriver();
		}
		else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			driver=new ChromeDriver();
		}
		//login to the application
		
		LoginPage lp=new LoginPage(driver);
		
		lp.loginToApplication(url,username, password);
		
		//click on Organization Module
		HomePage hp=new HomePage(driver);
		hp.getOrglink().click();
		
		//click on new Organization button(plus button)
		OrganizationPage op=new OrganizationPage(driver);
		op.getCreateorgbtn().click();
		
		//Enter all the details
		String orgname=elib.getDataFromExcelFile("org", 7, 2)+jlib.getRandomNumber();
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname);
		
		//go back to organization page by clicking on organization  link present in home page
		Thread.sleep(2000);
		hp.getOrglink().click();
		
		//search the orgname you want to delete
		op.deleteAlreadyCreatedOrg(orgname, "Organization Name");
		
		driver.findElement(By.xpath("//a[text()='"+orgname+"']/../..//td[8]/a[2]")).click();
		wlib.alterPopupAccept(driver);
		
		//verify 
		
		boolean actsearch=op.getNoorgtext().isDisplayed();
		if(actsearch) {
			System.out.println(orgname+" is verified====PASS");
		}
		else {
				System.out.println(orgname+" is not verified====FAIL");
			}
		
		
		//logout
		hp.singOutToApplication();
		
		Thread.sleep(200);
		
		driver.quit();

	}

}
