package com.kh.spring.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.kh.spring.member.model.service.MemberService;

@Controller
public class MemberCotroller {

	@Autowired
	private MemberService memberService;
	
	public String memberLogin() {
		
		
		
		
		return "";
	}
	
}
