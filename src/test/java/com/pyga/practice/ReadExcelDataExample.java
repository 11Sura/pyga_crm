package com.pyga.practice;



import com.pyga_crm.genericLib.CommondataFileLib;
import com.pyga_crm.genericLib.ExcelLib;

public class ReadExcelDataExample {
public static void main(String[] args) throws Throwable {
	ExcelLib elib=new ExcelLib();
	CommondataFileLib clib= new CommondataFileLib();
	String filepath = clib.getFilePathFromPropertiesFile("testScriptdataFilePath");
	System.out.println(filepath);
	String ven = elib.getDataFromExcelBasedTestId(filepath, "CampaignSystem", "Tc_01", "Vendor_Name");
	System.out.println(ven);

}
}
