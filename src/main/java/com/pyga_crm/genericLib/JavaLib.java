package com.pyga_crm.genericLib;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaLib {
	Random random = new Random();
	Date dateObj = new Date();
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	
	public int getRandomNumber() {
		int ranInt = random.nextInt(4000);
		return ranInt;
	}

	public String getDate() {

		String date = sim.format(dateObj);
		return date;
	}
	
	public String getDate(int requiredDateCount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, requiredDateCount);
		Date date = cal.getTime();
		String rdata = sim.format(date);
		return rdata;
	}

}
