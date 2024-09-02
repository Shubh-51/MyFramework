package com.comcast.crm.main.contacttest;

import java.io.IOException;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.excelutility.ExcelUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.genric.fileutility.FileUtility;
import com.comcast.crm.objectrepositry.ContactInfoPage;
import com.comcast.crm.objectrepositry.ContactPage;
import com.comcast.crm.objectrepositry.CreatingNewContactPage;
import com.comcast.crm.objectrepositry.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.LoginPage;

import com.comcast.crm.objectrepositry.OrganizationPage;

public class CreateContactWithOrg {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = null;
		FileUtility flib = new FileUtility();
		ExcelUtility elib = new ExcelUtility();
		JavaUtility jlib = new JavaUtility();

		String browser = flib.getDataFromPropertiesFile("browser");
		String url = flib.getDataFromPropertiesFile("url");
		String username = flib.getDataFromPropertiesFile("username");
		String password = flib.getDataFromPropertiesFile("password");

		if (browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("Edge")) {
			driver = new EdgeDriver();
		} else if (browser.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		// login to the application

		LoginPage lp = new LoginPage(driver);

		lp.loginToApplication(url, username, password);

		// click on Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on new Organization button(plus button)
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateorgbtn().click();

		// Enter all the details
		String orgname = elib.getDataFromExcelFile("contact", 7, 2) + jlib.getRandomNumber();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname);

		// click on contact Module
		Thread.sleep(2000);
		hp.getContactlink().click();

		// click on new Contact button(plus button)
		ContactPage cp = new ContactPage(driver);
		cp.getCreatecontactbtn().click();

		// Enter all the details
		String conname = elib.getDataFromExcelFile("contact", 7, 3) + jlib.getRandomNumber();
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createNewContactWithOrg(conname, orgname);

		// verify
		ContactInfoPage cip=new ContactInfoPage(driver);
		String headerinfo=cip.getHeaderinfo().getText();
		System.out.println(headerinfo);
		if(headerinfo.contains(conname)) {
			System.out.println(conname+" is verified====PASS");
		}else {
			System.out.println(conname+" is not verified====FAIL");
		}
		String actconname=cip.getEditlastname().getText();
		System.out.println(actconname);
		String actorgname=cip.getEditorgname().getText();
		
		System.out.println(actorgname);
		
		if((actconname.trim().equals(conname))&&(actorgname.contains(orgname))) {
			System.out.println(conname+" is verified====PASS");
			System.out.println(orgname+" is verified====PASS");
			}else {
				System.out.println(conname+" is not verified====FAIL");
			}

		

		// logout
		hp.singOutToApplication();

		Thread.sleep(200);

		driver.quit();

	}

}
