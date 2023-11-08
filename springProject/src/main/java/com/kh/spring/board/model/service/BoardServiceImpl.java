package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@EnableTransactionManagement
@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int selectListCount() {
		
		return boardDao.selectListCount(sqlSession);
	}

	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		
		int offset = (pi.getCurrentPage() - 1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset, pi.getBoardLimit());
		return boardDao.selectList(sqlSession, rowBounds);
	}

	@Override
	public int insertBoard(Board b) {
		return boardDao.insertBoard(sqlSession, b);
	}

	@Override
	public int increaseCount(int boardNo) {
		return boardDao.increaseCount(sqlSession, boardNo);
	}

	@Override
	public Board selectBoard(int boardNo) {
		Board b = boardDao.selectBoard(sqlSession, boardNo);
		return b;
	}

	@Override
	public int deleteBoard(int boardNo) {
		return boardDao.deleteBoard(sqlSession, boardNo);
	}

	@Override
	public int updateBoard(Board b) {
		return boardDao.updateBoard(sqlSession, b);
	}

	@Override
	public ArrayList<Reply> selectReplyList(int boardNo) {
		return boardDao.selectReplyList(sqlSession, boardNo);
	}

	@Override
	public int insertReply(Reply r) {
		return boardDao.insertReply(sqlSession, r);
	}

	//@Transactional 로 묶어주면 안에 있는 트랜잭션을 묶어 줌
	@Override
	public ArrayList<Board> selectTopBoard() {
		return boardDao.selectTopBoard(sqlSession);
	}

	
}
