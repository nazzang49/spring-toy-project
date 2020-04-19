package com.test.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * for naver login
 * @author ¹ÚÁø¿µ
 *
 */
public class CustomJsonParse {

	// (step2) get access token to access naver services
	public String getAccessToken(String data){
		String accessToken = "";

		try {

			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			accessToken = jsonObject.get("access_token").toString();
			
		} catch (Exception e) {
			accessToken = null;
			e.printStackTrace();
		}		
		return accessToken;
	}
	
	// (step1) get code to gain access token
	public String getCode(String data) {
		String code = "";

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			code = jsonObject.get("code").toString();
		} catch (Exception e) {
			code = null;
			e.printStackTrace();
		}
		return code;
	}
	
	public String getNaverSearchResult(String data) {

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(data);
			JSONObject code = (JSONObject)jsonObject.get("results");
			JSONArray jsonArray = (JSONArray)code.get("data");
						
			for(int i=0;i<jsonArray.size();i++) {
				JSONObject obj = (JSONObject)jsonArray.get(i);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}