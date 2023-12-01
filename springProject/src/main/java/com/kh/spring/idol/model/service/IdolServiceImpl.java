package com.kh.spring.idol.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.kh.spring.idol.model.dao.IdolRepository;
import com.kh.spring.idol.model.vo.Idol;
import com.kh.spring.idol.model.vo.ParentBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class IdolServiceImpl implements IdolService{

	private final IdolRepository idolRepository;
	private final SqlSessionTemplate sqlSession;
	
	@Override
	public List<Idol> selectIdols() {
		return idolRepository.selectIdols(sqlSession);
	}

	@Override
	public int insertIdol(Idol idol) {
		return idolRepository.insertIdol(sqlSession, idol);
	}

	@Override
	public int deleteIdol(String id) {
		return idolRepository.deleteIdol(sqlSession, id);
	}

	@Override
	public List<ParentBoard> selectBoardList(String category) {
		return idolRepository.selectBoardList(sqlSession, category);
	}

	@Override
	public ParentBoard selectBoard(int boardNo) {
		return idolRepository.selectBoard(sqlSession, boardNo);
	}
	
	
	
}
