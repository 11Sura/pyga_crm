package com.pyga.practice;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonPractice {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		JSONParser j=new JSONParser();
		Object jObj = j.parse(new FileReader("./data/commondata.json"));
		JSONObject jo=(JSONObject) jObj;
		System.out.println(jo.get("browser"));
		System.out.println(jo.get("url"));
		System.out.println(jo.get("username"));
		System.out.println(jo.get("password"));

	}

}
