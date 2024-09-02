package com.comcast.crm.main.organizationtest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.excelutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.genric.fileutility.FileUtility;
import com.comcast.crm.objectrepositry.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.LoginPage;
import com.comcast.crm.objectrepositry.OrganizationInfoPage;
import com.comcast.crm.objectrepositry.OrganizationPage;

public class CreateOrganizationWithIndustryAndTypeDD {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {

		 WebDriver driver=null;
		 FileUtility flib=new FileUtility();
		 ExcelUtility elib=new ExcelUtility();
		 JavaUtility jlib=new JavaUtility();
		
		
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
		String orgname=elib.getDataFromExcelFile("org", 4, 2)+jlib.getRandomNumber();
		String indusrty=elib.getDataFromExcelFile("org", 4, 3);
		String type=elib.getDataFromExcelFile("org", 4, 4);
		
		CreatingNewOrganizationPage cnop=new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname, indusrty, type);
		
		//verify 
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		String headerinfo=oip.getHeaderinfo().getText();
		
		if(headerinfo.contains(orgname)) {
			System.out.println(orgname+" is verified====PASS");
		}else {
			System.out.println(orgname+" is not verified====FAIL");
		}
		String actindustry=oip.getIndustrydd().getText();
		String acttype=oip.getTypedd().getText();
		if((actindustry.equals(indusrty))&&(acttype.equals(type))) {
			System.out.println(indusrty+" is verified====PASS");
			System.out.println(type+" is verified====PASS");
			}else {
				System.out.println(indusrty+" is not verified====FAIL");
				System.out.println(type+" is not verified====FAIL");
			}
		
		//logout
		hp.singOutToApplication();
		
		Thread.sleep(200);
		
		driver.quit();
		
	}
}