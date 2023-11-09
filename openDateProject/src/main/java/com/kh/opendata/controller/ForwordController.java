package com.kh.opendata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwordController {

	
	@RequestMapping("naver")
	public String naverPage() {
		return "naver/ppg";
	}
	
	@RequestMapping("kakao")
	public String kakaoMap() {
		return"kakao/map";
	}
	
	@RequestMapping("shelter")
	public String Tsunami() {
		return "shelter/shelterList";
	}
	
	@RequestMapping("map/{lat}/{lon}/{name}")
	public String shelterMap(@PathVariable("lat") @ModelAttribute String lat
							, @PathVariable("lon") @ModelAttribute String lon
							, @PathVariable("name") @ModelAttribute String name) {
		System.out.println(lat);
		System.out.println(lon);
		System.out.println(name);
		return "shelter/shelterMap";
	}
	
	@RequestMapping("busan")
	public String busan() {
		return "busan/welcome";
	}
	
	@RequestMapping("busanmap")
	public String busanMaps(String lat
							, String lon
							,String name
							, Model model) {
		model.addAttribute("lat", lat);
		model.addAttribute("lon", lon);
		model.addAttribute("name", name);
		
		System.out.println(lat);
		System.out.println(lon);
		System.out.println(name);
		
		return "shelter/shelterMap";
	}
	
	@RequestMapping("plant")
	public String plantList() {
		return "plant/plantList";
	}
	
	@RequestMapping("crops")
	public String cropsList() {
		
		return "plant/cropsList";
	}
	
	
	
}
