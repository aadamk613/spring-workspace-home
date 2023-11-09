package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class CropsController {

	@ResponseBody
	@RequestMapping(value="crops.wt", produces="text/html; charset=UTF-8")
	public String airPollution(String pageNo) throws IOException {
		
		String url = "https://apis.data.go.kr/1360000/FmlandWthrInfoService/getMmStatistics";
		url += "?serviceKey=" + AirController.SERVICEKEY;
		url += "&ST_YM=201601";
		url += "&ED_YM=201701";
		url += "&AREA_ID=999999999";
		url += "&PA_CROP_SPE_ID=999999999";
		url += "&dataType=XML";
		url += "&numOfRows=20";
		url += "&pageNo=" + pageNo;
		
		System.out.println("url : " + url);
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}
		
		System.out.println("responseText " + responseText);
		
		br.close();
		urlConnection.disconnect();
		return responseText;
				
	}
	
	
}


