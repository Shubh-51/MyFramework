package com.comcast.crm.testngassert.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.junit.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.comcast.crm.basetest.BaseClass2;
import com.comcast.crm.objectrepositry.ContactInfoPage;
import com.comcast.crm.objectrepositry.ContactPage;
import com.comcast.crm.objectrepositry.CreatingNewContactPage;
import com.comcast.crm.objectrepositry.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.OrganizationPage;

public class ContactTest2 extends BaseClass2{
	@Test(groups="SmokeTest")
	public void createContactTest() throws EncryptedDocumentException, IOException {
		
		//click on contact Module
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();
		
		//click on new Contact button(plus button)
		ContactPage cp=new ContactPage(driver);
		cp.getCreatecontactbtn().click();
		
		//Enter all the details
		String conname=elib.getDataFromExcelFile("contact", 1, 2)+jlib.getRandomNumber();
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createNewContact(conname);
		
		//verify 
		ContactInfoPage cip=new ContactInfoPage(driver);
		boolean headerinfo=cip.getHeaderinfo().getText().contains(conname);
		Assert.assertTrue(headerinfo);
		System.out.println("Pass");
		String actconname=cip.getEditlastname().getText();
		Assert.assertEquals(actconname, conname);
		System.out.println("Pass");
	}
	
	
	@Test(groups="RegressionTest")
	public void createContactWithSupportDateTest() throws EncryptedDocumentException, IOException {
		
		//click on contact Module
		
		HomePage hp=new HomePage(driver);
		hp.getContactlink().click();
		
		//click on new Contact button(plus button)
		ContactPage cp=new ContactPage(driver);
		cp.getCreatecontactbtn().click();
		
		//Enter all the details
		String conname=elib.getDataFromExcelFile("contact", 4, 2)+jlib.getRandomNumber();
		
		String startdate=jlib.getSystemDateInFormatYYYYMMDD();
		String enddate=jlib.reuiredDateInFormatYYYYMMDD(30);
		CreatingNewContactPage cncp=new CreatingNewContactPage(driver);
		cncp.createNewContactWithSupportDate(conname, startdate, enddate);
		
		//verify 
		ContactInfoPage cip=new ContactInfoPage(driver);
		
		String headerinfo=cip.getHeaderinfo().getText();
		
		if(headerinfo.contains(conname)) {
			System.out.println(conname+" is verified====PASS");
		}else {
			System.out.println(conname+" is not verified====FAIL");
		}
		
		String actstartdate=cip.getEditstartdate().getText();
		String actenddate=cip.getEditenddate().getText();
		SoftAssert sa= new SoftAssert();
		sa.assertEquals(actstartdate, startdate);
		sa.assertEquals(actenddate,enddate);
		sa.assertAll();
		
	}
	
	
	@Test(groups="SmokeTest")
	public void createContactWithOrganizationTest() throws EncryptedDocumentException, IOException, InterruptedException {

		// click on Organization Module
		HomePage hp = new HomePage(driver);
		hp.getOrglink().click();

		// click on new Organization button(plus button)
		OrganizationPage op = new OrganizationPage(driver);
		op.getCreateorgbtn().click();

		// Enter all the details
		String orgname = elib.getDataFromExcelFile("contact", 7, 3) + jlib.getRandomNumber();

		CreatingNewOrganizationPage cnop = new CreatingNewOrganizationPage(driver);
		cnop.createNewOrg(orgname);

		// click on contact Module
		Thread.sleep(2000);
		hp.getContactlink().click();

		// click on new Contact button(plus button)
		ContactPage cp = new ContactPage(driver);
		cp.getCreatecontactbtn().click();

		// Enter all the details
		String conname = elib.getDataFromExcelFile("contact", 7, 2) + jlib.getRandomNumber();
		CreatingNewContactPage cncp = new CreatingNewContactPage(driver);
		cncp.createNewContactWithOrg(conname, orgname);

		// verify
		ContactInfoPage cip=new ContactInfoPage(driver);
		boolean headerinfo=cip.getHeaderinfo().getText().contains(conname);
		SoftAssert sa=new SoftAssert();
		sa.assertTrue(headerinfo);
		String actconname=cip.getEditlastname().getText();
		
		String actorgname=cip.getEditorgname().getText();
		sa.assertEquals(actconname.trim(),conname);
		sa.assertEquals(actorgname.trim(),orgname);
		sa.assertAll();
	}
}
