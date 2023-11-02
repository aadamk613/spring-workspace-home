package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.common.model.vo.PageInfo;


public interface BoardService {

	// Plain Old Java Object
	
	// 게시글 목록 조회 + 페이징처리
	int selectListCount();
	
	// 게시글 리스트  조회
	ArrayList<Board> selectList(PageInfo pi);
	
	// 게시글 작성하기(INSERT)
	int insertBoard(Board b);
	
	// 게시글 상세보기
	// 게시글 조회수 증가(UPDATE)
	int increaseCount(int boardNo);
	
	// 게시글 상세조회(SELECT)
	Board selectBoard(int boardNo);
	
	// 게시글 삭제하기(UPDATE)
	int deleteBoard(int boardNo);
	
	// 게시글 수정하기(UPDATE)
	int updateBoard(int boardNo);
	
	// -------------------------------
	
	// 댓글 목록 조회
	
	// 댓글 작성
	
	// 메인 페이지 구성
	
}
