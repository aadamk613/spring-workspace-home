package com.kh.spring.member.model.vo;

import java.sql.Date;

public class Member {

	private String memberId;
	private String memberPwd;
	private String memberNm;
	private Date enrollDt;
	public Member(String memberId, String memberPwd, String memberNm, Date enrollDt) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.memberNm = memberNm;
		this.enrollDt = enrollDt;
	}
	public Member() {
		super();
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMemberNm() {
		return memberNm;
	}
	public void setMemberNm(String memberNm) {
		this.memberNm = memberNm;
	}
	public Date getEnrollDt() {
		return enrollDt;
	}
	public void setEnrollDt(Date enrollDt) {
		this.enrollDt = enrollDt;
	}

	
	
	

}
