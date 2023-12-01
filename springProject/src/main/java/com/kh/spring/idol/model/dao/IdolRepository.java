package com.kh.spring.idol.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.idol.model.vo.Idol;
import com.kh.spring.idol.model.vo.ParentBoard;

@Repository
public class IdolRepository {

	public List<Idol> selectIdols(SqlSessionTemplate sqlSession) {
		return sqlSession.selectList("idolMapper.selectIdols");
	}

	public int insertIdol(SqlSessionTemplate sqlSession, Idol idol) {
		return sqlSession.insert("idolMapper.insertIdol", idol);
	}

	public int deleteIdol(SqlSessionTemplate sqlSession, String id) {
		return sqlSession.delete("idolMapper.deleteIdol", id);
	}

	public List<ParentBoard> selectBoardList(SqlSessionTemplate sqlSession, String category) {
		return sqlSession.selectList("idolMapper.selectBoardList", category);
	}

	public ParentBoard selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		return sqlSession.selectOne("idolMapper.selectBoard", boardNo);
	}
	
	

}
