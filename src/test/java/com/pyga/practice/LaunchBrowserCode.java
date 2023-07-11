package com.pyga.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



public class LaunchBrowserCode {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("C:\\Users\\Sushma\\Desktop\\commondata.properties");
		
		Properties pObj=new Properties();
		pObj.load(fis);
		String Url=pObj.getProperty("url");
		
		System.out.println(Url);

	}

}
