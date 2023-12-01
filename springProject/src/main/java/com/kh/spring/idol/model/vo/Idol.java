package com.kh.spring.idol.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder 
@ToString
public class Idol {

	private Long id;
	private String name;
	private int member;
	private String song;
	private String image;
	private String description;
}
