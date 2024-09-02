package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int getRandomNumber() {
		Random random=new Random();
		int ranno=random.nextInt(1000);
	return ranno;		
	}
	
	public String getSystemDateInFormatYYYYMMDD() {
		
		Date dateobj=new Date();
		
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		
		String date=sim.format(dateobj);
		
	return date;
	}
	
	public  String reuiredDateInFormatYYYYMMDD(int day) {
		
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		String requieddate=sim.format(cal.getTime());
	return requieddate;	
	}
}
