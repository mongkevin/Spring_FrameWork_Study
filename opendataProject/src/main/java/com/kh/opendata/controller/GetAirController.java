package com.kh.opendata.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.Run;
import com.kh.opendata.model.vo.AirVo;

@Controller
public class GetAirController {
	
	@ResponseBody
	@RequestMapping(value="air.do",produces="application/json;charset=UTF-8")
	public String getAir(String location) throws IOException {
		String url ="https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		url += "?serviceKey="+Run.SERVICEKEY;
		url += "&sidoName="+URLEncoder.encode(location,"UTF-8");
		url += "&returnType=json";
		url += "&numOfRows=100";
		
		URL requestUrl= new URL(url);
		
		HttpURLConnection urlCon = (HttpURLConnection)requestUrl.openConnection();
		
		urlCon.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		
		String responseText = "";
		String line;
		while((line=br.readLine())!=null) {
			responseText += line;
		}
		
//		JsonObject totalObj = new JsonParser().parse(responseText).getAsJsonObject();
//		System.out.println(totalObj);
//		JsonArray itemArr = totalObj.getAsJsonObject("response").getAsJsonObject("body").getAsJsonArray("items");
//		System.out.println(itemArr);
//		ArrayList<AirVo> list = new ArrayList<>();
//		
//		for(int i=0; i<itemArr.size();i++) {
//			
//			JsonObject item = itemArr.get(i).getAsJsonObject();
//			
//			AirVo air = new AirVo();
//			
//			air.setStationName(item.get("stationName").getAsString());
//			air.setDataTime(item.get("dataTime").getAsString());
//			air.setKhaiValue(item.get("khaiValue").getAsString());
//			air.setPm10Value(item.get("pm10Value").getAsString());
//			air.setSo2Value(item.get("so2Value").getAsString());
//			air.setCoValue(item.get("coValue").getAsString());
//			air.setNo2Value(item.get("no2Value").getAsString());
//			air.setO3Value(item.get("o3Value").getAsString());
//			
//			list.add(air);
//		}
//		return new Gson().toJson(list);
		
		return responseText;
	}
	
//	@ResponseBody
//	@RequestMapping(value="air2.do",produces="text/html;charset=UTF-8")
//	public String getAir2(String location) throws IOException {
//		String url ="https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
//		url += "?serviceKey="+Run.SERVICEKEY;
//		url += "&sidoName="+URLEncoder.encode(location,"UTF-8");
//		url += "&returnType=xml";
//		url += "&numOfRows=50";
//		
//		URL requestUrl= new URL(url);
//		
//		HttpURLConnection urlCon = (HttpURLConnection)requestUrl.openConnection();
//		
//		urlCon.setRequestMethod("GET");
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
//		
//		String responseText = "";
//		String line;
//		while((line=br.readLine())!=null) {
//			responseText += line;
//		}
//		
//		return responseText;
//	}
//	
	private String servicekey = "a7f39d0b05156b2bdeb835ee65c40e2c";
	
	@ResponseBody
	@RequestMapping(value="movie.do",produces="application/json;charset=UTF-8")
	public String findMovie(String time) throws IOException {
//		https://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do
		String url ="http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json";
		url += "?key="+servicekey;
//		String time = new SimpleDateFormat("yyyyMMdd").format(new Date());
//		System.out.println(time);
		url += "&targetDt="+time;
//		url += "&itemPerPage=10";
		
		URL requestUrl= new URL(url);
		
		HttpURLConnection urlCon = (HttpURLConnection)requestUrl.openConnection();
		
		urlCon.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		
		String responseText = "";
		String line;
		while((line=br.readLine())!=null) {
			responseText += line;
		}
//		System.out.println(responseText);
		return responseText;
	}
}
