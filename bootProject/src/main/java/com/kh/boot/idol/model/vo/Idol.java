package com.kh.boot.idol.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString @AllArgsConstructor @Builder 
public class Idol {

	private int id;
	private String name;
	private int member;
	private String song;
	private String image;
	private String description;
	
}
