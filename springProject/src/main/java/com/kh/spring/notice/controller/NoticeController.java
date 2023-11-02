package com.kh.spring.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.common.template.Pagination;
import com.kh.spring.notice.model.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("list.no")
	public String selectList(@RequestParam(value="cPage", defaultValue="1")int currentPage 
							, Model model ){
		PageInfo pi = Pagination.getPageInfo(noticeService.selectListCount(), currentPage, 5, 5);
		
		model.addAttribute("list", noticeService.selectList(pi));
		System.out.println(noticeService.selectList(pi));
		System.out.println(pi);
		model.addAttribute("pi", pi);
		
		return "board/noticeListView";
	}
}
