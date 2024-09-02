package com.comcast.crm.generic.jsonutility;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONUtility {
	public String getDataFromJSONFile(String key) throws IOException, ParseException {
		FileReader fr=new FileReader("./configData/appcommondata.json");
		JSONParser parser=new JSONParser();
		Object obj=parser.parse(fr);
		JSONObject map=(JSONObject)obj;
		String value=map.get(key).toString();
	return value;
	}
}
