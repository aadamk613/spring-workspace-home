package com.kh.spring.idol.model.vo;

import java.sql.Date;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @ToString
public /* 버전 1 abstract*/ class ParentBoard {
	
	private int bno;
	private String title;
	private String content;
	private int type;
	private Date createDate;
	
	private List<IdolReply> replyList;
	
}
