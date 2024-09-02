package com.comcast.crm.testng.contacttest;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.Test;
import com.comcast.crm.basetest.BaseClass;
import com.comcast.crm.objectrepositry.ContactInfoPage;
import com.comcast.crm.objectrepositry.ContactPage;
import com.comcast.crm.objectrepositry.CreatingNewContactPage;
import com.comcast.crm.objectrepositry.CreatingNewOrganizationPage;
import com.comcast.crm.objectrepositry.HomePage;
import com.comcast.crm.objectrepositry.OrganizationPage;

public class ContactTest extends BaseClass{
	@Test
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
		String headerinfo=cip.getHeaderinfo().getText();
		
		if(headerinfo.contains(conname)) {
			System.out.println(conname+" is verified====PASS");
		}else {
			System.out.println(conname+" is not verified====FAIL");
		}
		String actconname=cip.getEditlastname().getText();
		if(actconname.equals(conname)) {
			System.out.println(conname+" is verified====PASS");
			}else {
				System.out.println(conname+" is not verified====FAIL");
			}
	}
	@Test
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
		
		if(actstartdate.equals(startdate)&&(actenddate.equals(enddate))) {
			System.out.println(startdate+" is verified====PASS");
			System.out.println(enddate+" is verified====PASS");
			}else {
				System.out.println(startdate+" is not verified====FAIL");
				System.out.println(enddate+" is not verified====FAIL");
			}
		
	}
	@Test
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


	}
}
