package com.pyga.practice;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAfter45DaysCode {

	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		cal.add(Calendar.DATE,45);
		Date date = cal.getTime();
		System.out.println(date);
		
		SimpleDateFormat simDate=new SimpleDateFormat("yyyy-MM-dd"); //month always in cap
		String fdate = simDate.format(date);
		System.out.println(fdate);

	}

}
