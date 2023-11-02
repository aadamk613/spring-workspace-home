package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok(롬복)
 * - 자동 코드 생성 라이브러리
 * - 반복되는 getter, setter, toString 등의 메소드 작성코드를 줄여주는
 * - 코드 다이어트 라이브러리
 * 
 * Lombok설치 방법
 * 1. 라이브러리 다운 후 적용(Maven pom.xml)
 * 2. 다운로드 된 jar파일을 찾아서 설치(작업할 IDE 체크)
 * 3. IDE 재실행
 * 
 * - Lombok 사용 시 주의 사항!!
 * - 앞글자가 외자인 필드명을 만들 경우
 * 
 * lombok의 명명 규칙 == getPName()
 * 
 * => EL표기법을 이용할 때 내부적으로 getter메소드를 검색하는 방법!
 * ${ pName } == getpName()
 * 필드명 작성 시 최소 소문자 두글자 이상으로 시작할 것!!
 * 
 * @Data
 * @NoArgsConsructor
 * @AllArgsConstructor
 * @Getter
 * @Setter
 * @ToString
 */
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Member {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age;
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;

}
