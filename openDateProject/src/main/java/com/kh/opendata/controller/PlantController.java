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
public class PlantController {

	static final String SERVICEKEY = "20231110TDYFKBQYDDNBRFX8VRFXW";
	@ResponseBody
	@RequestMapping(value="plant.do", produces="application/json; charset=UTF-8")
	public String airPollution(String cntntsNo) throws IOException {
		
		String url = "http://api.nongsaro.go.kr/service/garden/gardenDtl?";
		url += "apiKey=" + SERVICEKEY;
		url += "&cntntsNo=1";
		
		System.out.println(url);
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			responseText += line;
		}

		System.out.println(responseText);
		br.close();
		urlConnection.disconnect();
		System.out.println(responseText);
		return responseText;
				
	}
}
