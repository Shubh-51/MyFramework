package com.comcast.crm.generic.webdriverutility;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class WebDriverUtility {
	
	public void getWindowMaximize(WebDriver driver) {
		driver.manage().window().maximize();
	}
	
	public void WaitForPageToLoad(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}
	
	public void MouseHoverToElement(WebDriver driver,WebElement element) {
		Actions action=new Actions(driver);
		action.moveToElement(element).perform();
	}
	
	public void selectFromDropDown(WebElement element,String value) {
		Select sel=new Select(element);
		sel.selectByVisibleText(value);
	}
	
	public void alterPopupAccept(WebDriver driver) {
		driver.switchTo().alert().accept();
	}
	
	public void alterPopupDismiss(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}
	
	public void SwitchToWindowWithPartialUrl(WebDriver driver,String partialurl) {
		
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext()) {
			String id=it.next();
			driver.switchTo().window(id);
			String acturl=driver.getCurrentUrl();
			if(acturl.contains(partialurl)) {
				break;
			}
		}
	}

	public void SwitchToWindowWithPartailTitle(WebDriver driver, String partailtitle) {
		Set<String> set=driver.getWindowHandles();
		Iterator<String> it=set.iterator();
		while(it.hasNext()) {
			String id=it.next();
			driver.switchTo().window(id);
			String acttitle=driver.getTitle();
			if(acttitle.contains(partailtitle)) {
				break;
			}
		}
	}
	
	
}
