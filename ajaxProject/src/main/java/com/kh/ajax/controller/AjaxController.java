package com.kh.ajax.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Member;


@Controller
public class AjaxController {

	//1.HttpServletResponse 객체로 응답 데이터 응답하기(기존 servlet에서 사용하던 방식)
//	@RequestMapping("ajax1.do")
//	public void ajaxMethod1(String name, int age,HttpServletResponse response) throws IOException {
//		
//		//이후 요청처리를 다 진행했다고 가정하고
//		//응답데이터 돌려주기
//		
//		String responseData = "이름은 "+name+"이고 나이는 "+age+"살입니다.";
//		response.setContentType("text/html; charset=UTF-8");
//		response.getWriter().print(responseData);
//		
//	}
	
	//2.응답할 데이터를 문자열로 반환
	//기존에 문자열로 반환하면 원래는 포워딩 방식으로 진행이 됐었다. (응답뷰로 인식하여 해당 페이지를 찾으려 한다)
	//그래서 이 반환값은 응답페이지가 아니라 응답 데이터라고 하는것을 작성해줘야한다.
	//그 역할을 하는 어노테이션이 @ResponseBody 이다.
	@ResponseBody
	@RequestMapping(value="ajax1.do",produces="text/html;charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		
		String responseData = "이름은 "+name+"이고 나이는 "+age+"살입니다.";
		
		return responseData;
	}
	
	//다수의 응답데이터가 있을 경우
//	@RequestMapping("ajax2.do")
//	public void ajaxMethod2(int userNo,HttpServletResponse response) throws IOException {
		
		//요청처리가 다 되었다는 가정하고 진행
//		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().print("user01");
//		response.getWriter().print("김둘리");
		
		//JSON을 사용해서 응답하기
		//JSONArray => [값,값,...]
		//JSONObject => [키:값,키,값,...]
		
//		JSONArray arr = new JSONArray();
//		arr.add("user01");
//		arr.add("김둘리");
//		
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().print(arr);
		
//		JSONObject obj = new JSONObject();
//		obj.put("name", "김길동");
//		obj.put("age",20);
//		
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().print(obj);
//	}
	
	@ResponseBody
	@RequestMapping(value="ajax2.do",produces="application/json;charset=UTF-8")
	public String ajaxMethod2(int userNo) {
		
		//객체를 조회해왔다는 가정으로 진행
		Member m = new Member("도우너","user01",100);
		
//		JSONObject jobj = new JSONObject();
//		jobj.put("userId", m.getUserId());
//		jobj.put("userName",m.getUserName());
//		jobj.put("age", m.getAge());
		
//		return jobj.toJSONString();
		
		return new Gson().toJson(m);
	}
	
	@ResponseBody
	@RequestMapping(value="ajax3.do",produces="application/json;charset=UTF-8")
	public String ajaxMethod3() {
		
		//객체목록을 조회해왔다는 가정으로 진행
		ArrayList<Member> list = new ArrayList<>();
		
		list.add(new Member("도우너","user01",100));
		list.add(new Member("둘리","user02",120));
		list.add(new Member("희둥이","user03",5));
		list.add(new Member("또치","user04",33));
		
		return new Gson().toJson(list);
	}
}
