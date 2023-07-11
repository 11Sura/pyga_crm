package com.pyga.practice;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCode {

	public static void main(String[] args) {
		Date date=new Date(); //for system date
		System.out.println(date);
		
		SimpleDateFormat simDate=new SimpleDateFormat("yyyy-MM-dd"); //month always in cap
		String fdate = simDate.format(date);
		System.out.println(fdate);

	}

}
