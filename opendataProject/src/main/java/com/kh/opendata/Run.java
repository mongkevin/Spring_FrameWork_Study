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
import com.kh.opendata.model.vo.AirVo;

public class Run {

	//발급받은 인증키 변수처리(상수)
	public static final String SERVICEKEY ="iHNODidlYc5TFdq3ylvWT%2BQsJ%2FZFTPhOpifC0CF86YXoppkxaZ2WCp7iHbeL2FqtF%2F0WZZqe3Ns7qPqympKi7A%3D%3D";
	
	public static void main(String[] args) throws IOException {
		//OPEN API
		String url ="https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+SERVICEKEY;
		url += "&sidoName="+URLEncoder.encode("서울","UTF-8");
		url += "&returnType=json";
		
		//HttpURLConnection 객체를 이용하여 api요청 작업
		//1.요청하고자 하는 url를 전달하면서 java.net.URL 객체를 생성한다.
		URL requestUrl= new URL(url);
		
		//2.생성된 URL 객체로 HttpUrlConnection 객체 생성
		HttpURLConnection urlCon = (HttpURLConnection)requestUrl.openConnection();
		
		//3.요청에 필요한 Header 설정
		urlCon.setRequestMethod("GET");
		
		//4.해당 openAi 서버로 요청 후 스트림을 이용하여 응답에지터 읽어오기
		//보조스트림인 BufferedReader는 문자 스트림 (2byte)
		//기반스트림인 urlCon.getInputStream()은 1byte
		//보조스트림과 기반 스트림의 통로를 일치시켜야하기 때문에 inputStreamReader로 2byte 크기를 맞춰서 사용한다.
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		
		//5.반복적으로 응답데이터 읽어오기
		String responseText = "";
		String line;
		while((line=br.readLine())!=null) {
			responseText += line;
		}
		System.out.println(responseText);
		
		//전체 JSON형식으로부터 원하는 데이터만 추출해보기
		//응답데이터 Text를 JSONObject화 시키는 작업(파싱)
		JsonObject totalObj = new JsonParser().parse(responseText).getAsJsonObject();
		
		//전체 totalObj에서 response 속성명으로 접근해보기
		JsonObject responseObj = totalObj.getAsJsonObject("response");
		System.out.println(responseObj);//전체 JSON 형식으로부터 response 속성명으로 접근한 것 
		
		JsonObject bodyObj = responseObj.getAsJsonObject("body"); //response JSON형식으로 부터 body 속성명에 접근
		System.out.println(bodyObj);
		
//		JsonObject body = totalObj.getAsJsonObject("body"); 순차적으로 접근을 안하면 null값이 뜬다.
//		System.out.println(body); 
		
		//body에서 totalCount로 접근
		int totalCount = bodyObj.get("totalCount").getAsInt();
		System.out.println(totalCount);
		
		//body에서 items에 접근 (JSONArray 형태)
		JsonArray itemArr = bodyObj.getAsJsonArray("items");
		System.out.println(itemArr);
		
		System.out.println("===================");
		//items에 있는 각 item에 접근하기 위해 VO를 만들어 해당 필드에 넣어주기 후 리스트로 옮겨주기
		
		ArrayList<AirVo> list = new ArrayList<>();
		for(int i=0; i<itemArr.size();i++) {
			//item에 담겨있는 데이터 추출해서 담기
			
			//items에 있는 각 item부터 추출하기
			JsonObject item = itemArr.get(i).getAsJsonObject();
			
			System.out.println(item);
			
			AirVo air = new AirVo();
			
			air.setStationName(item.get("stationName").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air); //담은 vo 리스트에 추가
		}
		
		System.out.println(list);
		
		//list에 담긴 vo하나씩 출력
		for(AirVo a : list) {
			System.out.println(a);
		}
		
		//작업이 끝났으면 자원 반납 및 연결 해체
		br.close();
		urlCon.disconnect();
	}

}
