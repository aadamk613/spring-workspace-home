package com.kh.cookie.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {

	@RequestMapping("create")
	public String create(HttpServletResponse response) {
		
		// cookie는 name과 value가 필수!! expire는 선택!
		Cookie ck = new Cookie("newJeans", "mycookie");
		
		// name과 value는 무조건!! 모두 문자열만!!! 가능!!!(아스키코드)
		
		// 쿠키는 객체를 생성한 다음 응답정보에 첨부해야 발급
		
		// setMaxAge() == 만료시간
		ck.setMaxAge(60 * 60 * 24);
		
		response.addCookie(ck);
		
		return "cookie/create";
		
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletResponse response) {
		// 쿠키는 삭제 명령이 따로 없음
		// 만료시간을 0초로 설정해서 덮어씌워줄 것
		Cookie ck = new Cookie("newJeans", "deleteCookie"); // name속성만 같으면 끝
		ck.setMaxAge(0);
		response.addCookie(ck);
		return "cookie/create";
	}
	
	@RequestMapping("cookie")
	public String cookie() {
		return "cookie/cookie";
	}
	
	// 매핑값은 -넣어도 됨, 메소드명은 안됨
	@RequestMapping("sign-in")
	public String signIn(HttpServletResponse response) {
		
		Cookie saveId = new Cookie("saveId", "newJeans");
		saveId.setMaxAge(60 * 60 * 24 * 28);
		response.addCookie(saveId);
		return "cookie/sign-in";
	}
	
	@RequestMapping("ad")
	public String ad() {
		return "cookie/ad";
	}
	
	// 쿠키는 속도에 장점 => 사용자 단에서 처리하기 때문에
	// 세션은 안정성
	
	
}
