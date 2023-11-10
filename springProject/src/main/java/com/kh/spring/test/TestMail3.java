package com.kh.spring.test;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestMail3 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("sendmail")
	public String mail() throws MessagingException {
		
		// Mime message 생성
		
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
		
		String[] to = {"aadamk666@gmail.com"};
		/*
		String[] to = {"aadamk666@gmail.com","ajy020126@gmail.com", "kwondy1892@gmail.com", "jamie123112@gmail.com",
				"hajumosa1313@gmail.com","compie1176@gmail.com", "kh.rang73@gmail.com","checw0528@gmail.com","shl049576@gmail.com", "noje1123@gmail.com", 
				"khacademy1002@gmail.com", "hjj2977@gmail.com","leemj9987@gmail.com", "who2375@gmail.com", "jamie123112@gmail.com", "ofice89@gmail.com"};
				*/
		helper.setTo(to);
		helper.setSubject("형태가 있는 메일은 어떻게 생긴거??");
		//helper.setText("<h1 style='color:skyblue; font-size:50px;'>안녕!!!!!!</h1>", true);
		
		helper.setText("<a href='http://localhost:8002/spring/auth?authCode=1234'>여기로 오세요~~~</a>", true);
		sender.send(message);
		return "redirect:/";
	}
	
	@RequestMapping("auth")
	public String mail(String authCode) {
		if(authCode.equals("1234")) {
			System.out.println("인증성공~");
			
		}
		return "redirect:/";
	}
}
























