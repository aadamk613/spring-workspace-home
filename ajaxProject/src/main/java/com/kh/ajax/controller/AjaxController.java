package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.kh.ajax.model.vo.Member;

// 스프링 빈으로 입력 - 클래스 이름 위에
// 빈 스캐닝
@Controller
public class AjaxController {

	
	/*
	 * 1. HttpServletResponse 객체로 응답데이터 응답하기(Stream을 이용한 방식) - printWriter만들어서 
	 

	@RequestMapping("ajax.do")
	public void ajaxMethod1(String name, int age, HttpServletResponse response) { 
		
		System.out.println(name);
		System.out.println(age);
		
		// 요청 처리를 잘 했다는 가정하에! 요청 할 응답페이지에 반환할 데이터가 있다!!
		String responseData = "응답 데이터 : " + name + "은(는) " + age + "살 입니다.";
		response.setContentType("text/html; charset=UTF-8");
		try {
			response.getWriter().print(responseData);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 서블릿 만들었을 때는 서블릿에 throws에 예외처리가 되어 있어서 안 했었음, 여기서는 response를 만들어서 예외처리를 따로 해줘야함
		
		
	}
	*/
	
	/*
	 * 2. 응답할 데이터를 문자열로 반환
	 *  => HttpServletResponse를 사용하지 않는 방법
	 *  단, 문자열을 반환하면 원하던 포워딩 반식 => 응답뷰의 논리적인 경로로 인식을해서 뷰 리졸버로 전달
	 *  
	 *  따라서 내가 반환하는 String타입의 값이 뷰의 정보가 아니라 응답데이터다!!1 라는 것을 선언하는 애노테이션
	 *  @ResponseBody
	 */
	//@ResponseBody // 포워딩 한거 아니고 리스폰스 바디 영역에 붙인 데이터라는 애노테이션
	//@RequestMapping(/*속성명*/value="ajax.do", produces="text/html; charset=UTF-8")
	/*
	public String ajaxMethod1(String name, int age) {
		return "응답 문자열: " + name + age; // 디스패쳐 서블릿에 먼저 간 후 view리졸버에게 명령하면 .jsp어쩌구 경로 만들어서 디스패쳐 서블릿에게 다시 반환
	}
	*/
	
	
	// 다수의 응답데이터가 있을 경우?
	@RequestMapping("ajax.do")
	public void ajaxmethod1(String name, int age, HttpServletResponse response) throws IOException {

		// 요청처리를 잘했다는 가정하에 데이터 응답
	
		//response.setContentType("text/html; charset=UTF-8");
		//response.getWriter().print(name);
		//response.getWriter().print(age);
		// 출력하는 데이터가 하나의 문자열로 쭉 이어져있음
		
		// JSON(JavaScript Object Notation)형태로 담아서 응답 - 자바스크립트에서 활용가능하도록 파싱 가능함
		// JSONObject => {"속성":"값", "속성":"값", ... }
		// JSONArray => ["값", "값", "값", ... ]
		
		/* JSONArray
		
		JSONArray jArr = new JSONArray();
		jArr.add(name); // ["홍길동"]
		jArr.add(age); // ["홍길동", 15]
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jArr);
		*/
		
		/* JSONObject
		JSONObject jObj = new JSONObject();
		jObj.put("name", name); // {"name": "홍길동"}
		jObj.put("age", age); // {"name" : "홍길동", "age" : "14"}
		
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
		*/
		
		
	}
	/*
	@RequestMapping(value="ajax1.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod1(String name, int age) {
		JSONObject jObj = new JSONObject();
		jObj.put("name", name);
		jObj.put("age", age);
		return jObj.toJSONString(); // StringBuilder를 사용해서 요소들을 문자열로 만든 뒤 toString한 메소드를 반환 / JSONArray에서도 사용가능
	}
	
	/*
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(int num) {
		
		Member m = new Member("user01", "pass01", "홍길동", 15, "010-1234-1234"); // DB에서 조회해온 내
		
		// JSON형태로 만들어서 응답
		JSONObject jObj = new JSONObject();
		/*
		{
			"userId" : "user01",
			"userPwd" : "pass01",
			"userName" : "홍길동",
			"age" : 15,
			"phoneName" : "101-1234-1234"
		}
		
		jObj.put("userId", m.getUserId());
		jObj.put("userPwd", m.getUserPwd());
		jObj.put("userName", m.getUserName());
		jObj.put("age", m.getAge());
		jObj.put("phoneName", m.getPhoneNumber());
		
		//return jObj.toJSONString();
		return jObj.toString();
		//return (String) jObj.get("userId") +  (String) jObj.get("userPwd");
	}
	*/
	
	@ResponseBody
	@RequestMapping(value="ajax2.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod2(int num) {
		Member m = new Member("user01", "pass01", "홍길동", 15, "010-1234-1234");
		return new Gson().toJson(m);
	}
	
	
	@ResponseBody
	@RequestMapping(value="ajax3.do", produces="application/json; charset=UTF-8")
	public String ajaxMethod3() {
		
		ArrayList<Member> list = new ArrayList();
		list.add(new Member("user01", "pass01", "1길동", 15, "010-1234-1234"));
		list.add(new Member("user02", "pass02", "2길동", 25, "010-2234-1234"));
		list.add(new Member("user03", "pass03", "3길동", 35, "010-3234-1234"));
		
		return new Gson().toJson(list);
	}
	
	
	
	
	
	
	
	
	
	
}
