package com.kh.spring.idol.model.service;

import java.util.List;

import com.kh.spring.idol.model.vo.Idol;
import com.kh.spring.idol.model.vo.ParentBoard;

public interface IdolService {

	List<Idol> selectIdols();
	
	int insertIdol(Idol idol);
	
	int deleteIdol(String id);

	List<ParentBoard> selectBoardList(String category);

	ParentBoard selectBoard(int boardNo);

}
