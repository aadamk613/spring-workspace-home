package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CropsController {

	@ResponseBody
	@RequestMapping(value="crops.wt", produces="text/html; charset=UTF-8")
	public String airPollution(String pageNo) throws IOException {
		
		StringBuilder url = new StringBuilder("http://apis.data.go.kr/1360000/FmlandWthrInfoService/getDayStatistics"); /*URL*/
		url.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="); /*Service Key*/
		url.append(AirController.SERVICEKEY);
		url.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
		url.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
		url.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON)*/
		url.append("&" + URLEncoder.encode("ST_YMD","UTF-8") + "=" + URLEncoder.encode("20161201", "UTF-8")); /*일통계 시작 날짜(YYYYMMDD)*/
        url.append("&" + URLEncoder.encode("ED_YMD","UTF-8") + "=" + URLEncoder.encode("20171201", "UTF-8")); /*일통계 종료 날짜(YYYYMMDD)*/
        url.append("&" + URLEncoder.encode("AREA_ID","UTF-8") + "=" + URLEncoder.encode("4122000000", "UTF-8")); /*지역 아이디(활용가이드 하단첨부 참고)*/
        url.append("&" + URLEncoder.encode("PA_CROP_SPE_ID","UTF-8") + "=" + URLEncoder.encode("PA130201", "UTF-8")); /*주산지 작물별 특성 아이디(활용가이드 하단첨부)*/
		
		
		/*
		String url = "https://apis.data.go.kr/1360000/FmlandWthrInfoService/getMmStatistics";
		url += "?serviceKey=" + AirController.SERVICEKEY;
		url += "&ST_YM=201601";
		url += "&ED_YM=201701";
		url += "&AREA_ID=999999999";
		url += "&PA_CROP_SPE_ID=999999999";
		url += "&dataType=XML";
		url += "&numOfRows=20";
		url += "&pageNo=" + pageNo;
		*/
        
		System.out.println("url : " + url);
		
		URL requestUrl = new URL(url.toString());
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


