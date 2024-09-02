package com.comcast.crm.basetest;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.databaseutility.DatabaseUtility;
import com.comcast.crm.generic.excelutility.ExcelUtility;
import com.comcast.crm.generic.jsonutility.JSONUtility;
import com.comcast.crm.generic.webdriverutility.JavaUtility;
import com.comcast.crm.generic.webdriverutility.WebDriverUtility;
import com.comcast.crm.genric.fileutility.FileUtility;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.LoginPage;

public class BaseClass3 {
	public WebDriver driver=null;
	public DatabaseUtility dlib=new DatabaseUtility();
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
	public JSONUtility jsonlib=new JSONUtility();
	public WebDriverUtility wlib=new WebDriverUtility();
	public JavaUtility jlib=new JavaUtility();
	
	@BeforeSuite(groups={"SmokeTest","RegressionTest"})
	public void configBS() {
		System.out.println("======Connect To DB,ConfigReport=======");
		dlib.getConnectionOfDatabase();
	}
	
	
	@BeforeClass(groups={"SmokeTest","RegressionTest"})
	public void configBC() throws IOException {
		System.out.println("======Launch The Browser=======");
		String browser=System.getProperty("browser");
		if(browser.equals("chrome")) {
			driver=new ChromeDriver();
		}
		else if(browser.equals("edge")) {
			driver=new EdgeDriver();
		}
		else if(browser.equals("firefox")) {
			driver=new FirefoxDriver();
		}
		else {
			driver=new ChromeDriver();
		}
	}
	
	
	@BeforeMethod(groups={"SmokeTest","RegressionTest"})
	public void configBM() throws IOException {
		System.out.println("======Login The Application=======");
		//String url=flib.getDataFromPropertiesFile("url");
		//String username=flib.getDataFromPropertiesFile("username");
		//String password=flib.getDataFromPropertiesFile("password");
		String url=System.getProperty("url");
		String username=System.getProperty("username"));
		String password=System.getProperty("password");
		
        LoginPage lp=new LoginPage(driver);
		
		lp.loginToApplication(url,username, password);
		
	}
	
	
	@AfterMethod(groups={"SmokeTest","RegressionTest"})
	public void configAM() {
		HomePage hp=new HomePage(driver);
		hp.singOutToApplication();
		System.out.println("=====Logout From The Aplication=====");
	}
	
	
	@AfterClass(groups={"SmokeTest","RegressionTest"})
	public void configAC() {
		driver.quit();
		System.out.println("=====Close The Browser=====");
	}
	
	
	@AfterSuite(groups={"SmokeTest","RegressionTest"})
	public void configAS() {
		dlib.closeDatabaseConnection();
		System.out.println("=====Close The Connection=====");
	}
}
