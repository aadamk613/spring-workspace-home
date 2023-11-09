package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BusanController {

	
	@ResponseBody	
	@RequestMapping(value="busan.taste", produces="application/json; charset=UTF-8")
	public String busanFood(int pageNo) throws IOException {
		String url = "http://apis.data.go.kr/6260000/FoodService/getFoodKr";
			   url+= "?serviceKey=" + AirController.SERVICEKEY;
			   url+= "&numOfRows=10";
			   url+= "&resultType=json";
			   url+= "&pageNo" + pageNo;
			   
	URL requestUrl = new URL(url);
	HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
	urlConnection.setRequestMethod("GET");
	BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
	
	String responseText = "";
	String line;
	while((line = br.readLine()) != null) {
		responseText += line;
	}
	
	br.close();
	urlConnection.disconnect();
	return responseText;
	
	}
}
