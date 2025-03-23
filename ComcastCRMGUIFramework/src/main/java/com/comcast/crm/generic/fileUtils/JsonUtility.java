package com.comcast.crm.generic.fileUtils;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonUtility {
	
	public String getDataFromJsonFile(String key) throws Throwable, org.json.simple.parser.ParseException{
		
		FileReader fileR = new FileReader(IPathConstants.jsonPath);
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(fileR);
		JSONObject map = (JSONObject) obj;
		String data = (String) map.get(key);
		return data;
	}
}