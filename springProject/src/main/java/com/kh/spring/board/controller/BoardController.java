package com.kh.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.board.model.service.BoardService;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 메뉴바의 게시판 클릭 시 => /list.bo
	// 페이징 바 클릭 시 => /list/bo?cPage=요청하는 페이지의 번호
	@RequestMapping("list.bo")
	public String selectList(@RequestParam(value="cPage", defaultValue="1")int currentPage, Model model) {
		
		PageInfo pi = Pagination.getPageInfo(boardService.selectListCount(), currentPage, 5, 5);
		
		// session, 그냥 model , modelAndView 중에 하나에 담아야 함
		model.addAttribute("list", boardService.selectList(pi));
		model.addAttribute("pi", pi);
		
		return "board/boardListView";
	}
	
	@RequestMapping("enrollForm.bo")
	public String enrollForm() {
		return "board/boardEnrollForm";
	}
	
	@RequestMapping("insert.bo")
	public String insertBoard(Board b
							, MultipartFile upfile // 여러개의 첨부파일을 전달 받을 시 MultipartFile[]로 받을 수 있음
							, HttpSession session
							, Model model) {
		
		//System.out.println(b);
		//System.out.println(upfile);
		
		// 첨부파일이 있건 없건 무조건 객체가 생성!(차이점 filename필드에 원본명이 존재하는가 /""인가)
		
		// 전달된 파일이 존재할 경우! => 파일명 수정 후 서버에 업로드
		
		
		if(!upfile.getOriginalFilename().equals("")) {
			
			// Board객체의 originName + changeName
			b.setOriginName(upfile.getOriginalFilename());
			b.setChangeName(saveFile(upfile, session));
			
		}
		
		// 넘어온 첨부파일이 존재하지 않을 경우 b : 제목, 작성자, 내용
		// 넘어온 첨부파일이 존재할 경우 b : 제목, 작성자, 내용, 원본명, 저장경로 + 바뀐이름
		if(boardService.insertBoard(b) > 0) { // 성공
			session.setAttribute("alertMsg", "게시글 작성 성공");
			return "redirect:list.bo";
		} else {
			model.addAttribute("errorMsg", "게시글 작성 실패");
			return "common/errorPage";
		}
			
		
		
			/*
			// => 파일의 원본명, 서버의 업로드 할 경로 + 바뀐 이름을 b에 이어서 담기
			
			// 파일명 수정 작업 후 서버에 업로드시키기("bono.jpg" => 2023110312398290.jpg)
			String originName = upfile.getOriginalFilename();
			
			// "20231101~~" 년월일시분초
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			
			/*
			 * 자바 개발자
			 * 
			 * == 자바를 이용해서 데이터 다루기
			 * 
			 * 1. 정수데이터
			 * 2. 문자열데이터
			 * 3. 날짜데이터
			 * 
			 * 4. JSON데이터
			 * 
			 */

		/*
			// 234543(5자리 랜덤값)
			int ranNum = (int)Math.random() * 9000 + 10000;
			
			// 이걸로 만들 수도 있음 Random r =
			
			// 확장자
			String ext = originName.substring(originName.lastIndexOf("."));
			
			String changeName = currentTime + ranNum + ext;
			
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");

			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		}
		*/
		
	}
	
	public String saveFile(MultipartFile upfile, HttpSession session) {
			
		String originName = upfile.getOriginalFilename();
			String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
			int ranNum = (int)Math.random() * 9000 + 10000;
			
			String ext = originName.substring(originName.lastIndexOf("."));
			String changeName = currentTime + ranNum + ext;
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");

			try {
				upfile.transferTo(new File(savePath + changeName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			return "resources/uploadFiles/" + changeName;
		}
	
	@RequestMapping("detail.bo")    							  // int형으로 작성했더니 알아서 파싱
	public ModelAndView selectBoard(/*식별하는데 필요한 값을 가지고 와야 함 */int boardNo, ModelAndView mv) { 
		
		// 해당 게시글 조회수 증가시키는 서비스 호출 결과(update)
		if(boardService.increaseCount(boardNo) > 0) {
			// 성공적으로 조회수 증가
			// boardDetailView.jsp상에 필요한 데이터를 조회(게시글 상세정보 조회용 서비스 호출)
			// 		>> 조회된 데이터를 담아서 board//boardDetailView로 포워딩
			
			Board b = boardService.selectBoard(boardNo);
			System.out.println(b);
			mv.addObject("b", b);
			mv.setViewName("board/boardDetailView");
			
		} else {
			// 조회수 증가 실패
			// 		>> 에러 문구를 담아서 에러페이지로 포워딩
			mv.addObject("errorMsg", "게시글 조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	/**
	 * deleteBoard : 사용자에게 게시글 번호를 전달 받아서  Board테이블에 STATUS컬럼의 값을 N으로 바꿔주는 메소드
	 * 
	 * @param bno : 삭제 요청을 받은 게시글의 번호
	 * @param session : 응답 시 전달값을 담기 위한 HTTP타입 세션 객체
	 * @param filePath : 게시글 삭제 성공 시 첨부파일을 제거하기 위한 파일이 저장되어있는 경로 및 파일명
	 * @return : 반환될 View의 논리적인 경로
	 */
	@RequestMapping("delete.bo")
	public String deleteBoard(int bno, HttpSession session, String filePath) {
		
		if(boardService.deleteBoard(bno) > 0) {
			if(!filePath.equals("")) {
				// 기존에 존재하는 첨부파일을 삭제
				// resources/xxxx/xxxx.jpg
				new File(session.getServletContext().getRealPath(filePath)).delete();
			}
			session.setAttribute("alertMsg", "삭제 성공");
			return "redirect:list.bo";
		} else {
			session.setAttribute("errorMsg", "삭제에 실패하였습니다.");
			return "common/errorPage";
		}
	}
	
	// @GetMapping도 있음
	@RequestMapping("updateForm.bo")
	public ModelAndView updateBoard(int bno, ModelAndView mv) {
		
		mv.addObject("b", boardService.selectBoard(bno)).setViewName("board/boardUpdateForm");
		return mv;
	}
	
	@RequestMapping("update.bo")
	public String updateBoard(/* @ModelAttribute 가시성을 위해 작성할 수 있음 */Board b
								, MultipartFile reUpfile
								, HttpSession session) {
		System.out.println("수정 받아온 값 " +b);
		/*
		 * 1. 새로 첨부파일 X, 기존 첨부파일 X => origin : null
		 * 
		 * 2. 새로 첨부파일 X, 기존 첨부파일 O => origin : 기존 첨부파일 이름, change : 기존 첨부파일 경로
		 * 
		 * 3. 새로 첨부파일 O, 기존 첨부파일 X => origin : 새로운 첨부파일 이름, change : 새로운 첨부파일 경로
		 * 
		 * 4. 새로 첨부파일 O, 기존 첨부파일 O => origin : 새로운 첨부파일 이름, change : 새로운 첨부파일 경로
		 * 
		 * 
		 */
		// 새로운 첨부파일을 첨부한 경우
		if(!reUpfile.getOriginalFilename().equals("")) {
			
			// 기존에 첨부파일이 존재했는지 체크 => 기존의 첨부파일 삭제
			if(b.getOriginName() != null) {
				new File(session.getServletContext().getRealPath(b.getChangeName()));
			}
			
			// 새로 넘어온 첨부파일 서버에 업로드 시키기
			// saveFile()
			b.setOriginName(reUpfile.getOriginalFilename());
			b.setChangeName(saveFile(reUpfile, session));
			// b라는 Board 타입객체에 새로운 정보(원본파일명, 저장경로+바뀐이름) 담기
		}
		
		if(boardService.updateBoard(b) > 0) {
			session.setAttribute("alertMsg", b);
			return "redirect:detail.bo?boardNo=" + b.getBoardNo();
		} else {
			session.setAttribute("alertMsg", "게시글 수정에 실패하였습니다.");
			return "common/errorPage";
		}
		
	}
	
	
}
