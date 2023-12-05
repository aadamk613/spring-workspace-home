package com.kh.boot.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.boot.idol.model.service.IdolService;
import com.kh.boot.idol.model.vo.Idol;

import lombok.RequiredArgsConstructor;

@CrossOrigin("*")
@RestController
@RequestMapping("/idol")
@RequiredArgsConstructor
public class IdolController {

	private final IdolService idolService;
	
	@GetMapping
	public ResponseEntity<List<Idol>> selectAll(){
		List<Idol> idolList = idolService.selectAll();
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<List<Idol>>(idolList, header, HttpStatus.OK);
	}
	
	
}
