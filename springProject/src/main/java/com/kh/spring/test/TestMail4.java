package com.kh.spring.test;

import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestMail4 {

	@Autowired
	private JavaMailSender sender;
	
	@RequestMapping("sendfile")
	public String sendFile() throws MessagingException {
	
		// MimeMessage를 이용해서 파일 첨부
		
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		String[] to = {"aadamk666@gmail.com","ajy020126@gmail.com", "kwondy1892@gmail.com", "jamie123112@gmail.com",
				"hajumosa1313@gmail.com","compie1176@gmail.com", "kh.rang73@gmail.com","checw0528@gmail.com","shl049576@gmail.com", "noje1123@gmail.com", 
				"khacademy1002@gmail.com", "hjj2977@gmail.com","leemj9987@gmail.com", "who2375@gmail.com", "jamie123112@gmail.com", "ofice89@gmail.com"};
		helper.setTo(to);
		helper.setSubject("파일을 받으렴~~");
		//helper.setText("<h1 style='color:skyblue; font-size:50px;'>안녕!!!!!!</h1>", true);
		
		helper.setText("<h1 style='color:green; font-size:50px;'>귀여운거 많아요 사러오세요</h1> <br> <a href='https://www.dotorisup.com/'>도토리숲</a>", true);
		
		DataSource source = new FileDataSource("C:/frontend-workspace/1.jpg");
		helper.addAttachment(source.getName(), source);
				
		sender.send(message);
		return "redirect:/";
	}
}
