package com.kh.spring.idol.controller;

import java.nio.charset.Charset;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.spring.idol.model.service.IdolService;
import com.kh.spring.idol.model.vo.Idol;
import com.kh.spring.idol.model.vo.ParentBoard;

import lombok.RequiredArgsConstructor;

@RequestMapping("/idol")
@CrossOrigin("*") // 어디서 오는 요청이든 받겠다
@RestController // 뷰 리졸버를 갈 일이 없음, 컨트롤러의 역할을 하는것은 맞는데, 응답할 때 무조건 데이터로 응답 == @ResponseBody생략 가능
@RequiredArgsConstructor // 롬복에 포함된 것, 이걸 달면 내가 가지고 있는 모든 필드를 포함한 매개변수 생성자를 만들어 줌
public class IdolController {

	// 필드 주입 => 실무자들이 편리하니까, 바쁘니까 많이 쓰는 방법 
	// 책 425페이지 보기~ == 필드 인젝션말고 다른거 나와있음
	@Autowired
	private final IdolService idolService; // 얘는 참조자료형이고, Autowired 선언 안해주면 기본적으로는 null

	// setter 주입
	// setter의 목적 == 캡슐화가 되어있는 필드에 밖에서 접근하지 못하게 하기
	// 여기도 Autowired붙일 수 있음
	// 개념적으로 setter가 꼭 필요한가?에 대한 의문이 있음.., 우리는 mybatis때문에 쓰긴 해야함
	// setter가 있다면 필드 불변성이 보장되지 않음 
	// 잘 쓰지도 않음
	/*
	@Autowired
	public void setIdolService(IdolService idolService) {
		this.idolService = idolService;
	}
	*/
	
	/*
	// 생성자 주입** 가장 권장하는 방법, 인텔리제이는 필드주입으로 쓰면 노란줄 뜨면서 권고도 함~
	// 필드를 매개변수로 가지고 있는 생성자
	// 이걸 만드는 순간 기본생성자를 직접 만들어줘야하고, 만들어주지 않으면 기본생성자는 사용 불가
	// 장점: 1. 필드 주입과 비교해보았을 때 IdolService는 항상 값이 들어있음(null이 없음, 억지로 넣지 않는 이상)
	// 2. final로 선언 할 수 있음(이는 변하지 않음을 의미(==불변성 보존)) 
	// 객체의 불변성도 보존할 수 있고, null이 들어가는 실수도 방지할 수 있음
	@Autowired
	public IdolController(IdolService idolService) {
		this.idolService = idolService;
	}
	*/	
	
	// ResponseEntity: 스프링에서 자체적으로 제공하는 데이터 반환시 사용할 수 있는 타입 
	// 돌려줄수 있는 값: 돌려보내 줄 데이터, 헤더의 정보, 응답 코드
	// HttpHeaders도 스프링 제공
	//@GetMapping(value="/idols")
	public ResponseEntity<List<Idol>> selectIdols() {
		
		List<Idol> idolList = idolService.selectIdols();
		System.out.println(idolList);
		HttpHeaders header = new HttpHeaders(); // 매핑값 produced에 넣는 값이 원래 header에 들어가는 값이기 때문에 이 값들을 넣어 줄 수 있음
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<List<Idol>>(idolList, header, HttpStatus.OK);
		// 앞단에서 json형태 데이터가 필요하면 매핑값 idols로 요청을 보내면 됨
		// 자바스크립트 이용해서 ajax요청, jquery로 요청을 보냄
		// 리액트에서도 jquery를 사용 할 수 있으나 굳이 쓰지는 않음
		// vs에서 npm install axios하기
	}
	
	//@PostMapping("idol") // post형식을 httpBody에 담기기 때문에 애노테이션을 달아줌
	public ResponseEntity<String> insertIdol(@RequestBody Idol idol) {
		// 객체명을 react와 같게 만들어놓아서 커맨드객체 방식으로 사용 가능함
		String result = idolService.insertIdol(idol) != 0 ? "success" : "fail";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(result, header, HttpStatus.OK);
	}
	
	@DeleteMapping("/idol/{id}")
	public ResponseEntity<String> deleteIdol(@PathVariable(name="id") String id) {
		System.out.println(id);
		String result = idolService.deleteIdol(id) != 0 ? "success" : "fail";
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("text", "html", Charset.forName("UTF-8")));
		return new ResponseEntity<String>(result, header, HttpStatus.OK);

	}
	
	@GetMapping("/idolboard/{category}")
	public ResponseEntity<List<ParentBoard>> selectBoardList(@PathVariable(name="category") String category) {
		List<ParentBoard> list = idolService.selectBoardList(category);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<List<ParentBoard>>(list, header, HttpStatus.OK);
	}	

	@GetMapping("/boardDetail/{boardNo}")
	public ResponseEntity<ParentBoard> selectBoard(@PathVariable(name="boardNo") int boardNo){
		
		ParentBoard board = idolService.selectBoard(boardNo);
		HttpHeaders header = new HttpHeaders();
		header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
		return new ResponseEntity<ParentBoard>(board, header, HttpStatus.OK);
	}
	
	
	
	
	
}
