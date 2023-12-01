package com.kh.spring.idol.model.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public class PhotoBoard /* 버전 1 extends ParentBoard */ {
	
	private String image;
	
	private ParentBoard parentBoard;
}
