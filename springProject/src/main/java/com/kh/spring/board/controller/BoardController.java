package com.kh.spring.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.board.model.service.BoardService;
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
		System.out.println(boardService.selectList(pi));
		System.out.println(pi);
		model.addAttribute("pi", pi);
		
		return "board/boardListView";
	}
	
	
	
}
