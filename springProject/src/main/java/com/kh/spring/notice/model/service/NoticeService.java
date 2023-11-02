package com.kh.spring.notice.model.service;

import java.util.ArrayList;

import com.kh.spring.common.model.vo.PageInfo;
import com.kh.spring.notice.model.vo.Notice;

public interface NoticeService {

	// 공지사항 페이징 처리
	int selectListCount();
	
	// 공지사항 리스트 조회
	ArrayList<Notice> selectList(PageInfo pi);
	
	// 공지사항 상세보기
	Notice selectNotice();
}
