package com.kh.opendata;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

public class AirPollutionJavaApp {

	static final String SERVICEKEY = "URVZtGWbCxrS0aRaW5B9IIihfxuygs0SyeQ2xfz1xlyLXGKBEr4lADwqNSKV0ldhG1%2BO0NTJjLWjhARp8LAruA%3D%3D";
	
	public static  void main(String[] args) throws IOException{
		
		// OpenAPI서버로 요청
		// url만들기!
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey=" + SERVICEKEY;
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8");
		url += "&returnType=json";
		// json은 응답데이터가 한 줄로 온다 => responseText를 br.readLine()으로 읽으면된다
		
		System.out.println(url);
		
		// 자바코드로 요청 해야함!!
		// **HttpURLConnection 객체를 활용해서 OpenAPI요청 해보기
		// 1. 요청하고자 하는 url을 전달하면서 java.net.URL객체 생성
		URL requestUrl = new URL(url);
		// 2. 1번과정으로 생성된 URL객체를 가지고 HttpURLConnection객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		// 3. 요청에 필요한 Header설정
		urlConnection.setRequestMethod("GET");
		// 4. 해당 OpenAPI서버로 스트림을 연결해서 입력 데이터 읽어오기 // 버퍼리더(BufferedReader)는 바이트 보조 스트림이기 때문에 그걸 2바이트 스트림으로 바꿔주는 보조스트림(InputStreamReader)을 또 써야한다
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			
		String responseText = "";
		/*
		String line;
		while((line = br.readLine()) != null ) {
			responseText += line + "\n";
		}
		System.out.println(responseText);		
		*/
		responseText = br.readLine();
		System.out.println(responseText);
		
		/*
		 * https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty?serviceKey=URVZtGWbCxrS0aRaW5B9IIihfxuygs0SyeQ2xfz1xlyLXGKBEr4lADwqNSKV0ldhG1%2BO0NTJjLWjhARp8LAruA%3D%3D&sidoName=%EC%84%9C%EC%9A%B8&returnType=json
		  {"response"
		  			:{"body"
		  					:{"totalCount":40,
		  					"items":[
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"43","so2Value":"0.003","coValue":"0.4","pm10Flag":null,"o3Grade":"1","pm10Value":"21","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.026","stationName":"중구","pm10Grade":"1","o3Value":"0.025"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"44","so2Value":"0.003","coValue":"0.5","pm10Flag":null,"o3Grade":"1","pm10Value":"21","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.025","stationName":"한강대로","pm10Grade":"1","o3Value":"0.026"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"52","so2Value":"0.003","coValue":"0.4","pm10Flag":null,"o3Grade":"2","pm10Value":"15","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.016","stationName":"종로구","pm10Grade":"1","o3Value":"0.032"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"50","so2Value":"0.004","coValue":"0.6","pm10Flag":null,"o3Grade":"1","pm10Value":"25","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.027","stationName":"청계천로","pm10Grade":"1","o3Value":"0.024"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"42","so2Value":"0.003","coValue":"0.5","pm10Flag":null,"o3Grade":"1","pm10Value":"17","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.021","stationName":"종로","pm10Grade":"1","o3Value":"0.025"},
		  					{"so2Grade":null,"coFlag":"통신장애","khaiValue":"-","so2Value":"-","coValue":"-","pm10Flag":"통신장애","o3Grade":null,"pm10Value":"-","khaiGrade":null,"sidoName":"서울","no2Flag":"통신장애","no2Grade":null,"o3Flag":"통신장애","so2Flag":"통신장애","dataTime":"2023-11-08 14:00","coGrade":null,"no2Value":"-","stationName":"용산구","pm10Grade":null,"o3Value":"-"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"42","so2Value":"0.002","coValue":"0.4","pm10Flag":null,"o3Grade":"1","pm10Value":"21","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.025","stationName":"광진구","pm10Grade":"1","o3Value":"0.024"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"47","so2Value":"0.003","coValue":"0.3","pm10Flag":null,"o3Grade":"1","pm10Value":"28","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.023","stationName":"성동구","pm10Grade":"1","o3Value":"0.022"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"60","so2Value":"0.003","coValue":"0.6","pm10Flag":null,"o3Grade":"1","pm10Value":"28","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"2","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.036","stationName":"강변북로","pm10Grade":"1","o3Value":"0.015"},
		  					{"so2Grade":"1","coFlag":null,"khaiValue":"44","so2Value":"0.002","coValue":"0.3","pm10Flag":null,"o3Grade":"1","pm10Value":"15","khaiGrade":"1","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-11-08 14:00","coGrade":"1","no2Value":"0.018","stationName":"중랑구","pm10Grade":"1","o3Value":"0.027"}],
		  					"pageNo":1,"numOfRows":10},"header":{"resultMsg":"NORMAL_CODE","resultCode":"00"}}}

		 */
		
		// 라이브러리
		// JSONObject, JSONArray => JSON라이브러리에서 제공하는 클래스
		// JsonObject, JsonArray, JsonElement => Gson에서 제공, 2.8.6부터 제공
		
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		//System.out.println(totalObj);
		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response속성에 접근 => {} JsonObject
		//System.out.println(responseObj);
		JsonObject bodyObj = responseObj.getAsJsonObject("body");
		//System.out.println(bodyObj);
		int totalCount = bodyObj.get("totalCount").getAsInt(); // getAsInt()바로 해도 됨
		//System.out.println(totalCount);
		JsonArray itemArr = bodyObj.getAsJsonArray("items");
		//System.out.println(itemArr);
		
		//JsonArray()는 ArrayList<JsonElement> 이다
		ArrayList<AirVO> list = new ArrayList(); 
		
		for(int i = 0; i < itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			System.out.println("item" + item);
			
			AirVO air = new AirVO();
			
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			
			list.add(air);
		}

		for(AirVO air : list) {
			System.out.println(air);
		}
		
		
		// 다사용한 객체 반납
		br.close();
		urlConnection.disconnect();
		
		
		
		
		
		
		
		
	}
}
