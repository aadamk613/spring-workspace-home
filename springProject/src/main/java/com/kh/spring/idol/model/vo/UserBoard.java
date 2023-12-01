package com.kh.spring.idol.model.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter @Setter @NoArgsConstructor @ToString
public class UserBoard  /* 버전 1 extends ParentBoard */ {

	private String writer;
	
	private ParentBoard parentBoard;
}
