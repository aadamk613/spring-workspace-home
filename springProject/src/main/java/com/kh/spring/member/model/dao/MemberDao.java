package com.kh.spring.member.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.member.model.vo.CertVO;
import com.kh.spring.member.model.vo.Member;

// Repository : 저장소
@Repository // 저장소와 관련된 작업(영속성)을 처리하겠다는 뜻
public class MemberDao {
	
	public Member loginMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.selectOne("memberMapper.loginMember", m);
	}

	public int insertMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.insert("memberMapper.insertMember", m);
	}
	
	public int updateMember(SqlSessionTemplate sqlSession, Member m) {
		return sqlSession.update("memberMapper.updateMember", m);
	}
	
	public int deleteMember(SqlSessionTemplate sqlSession, String userId) {
		return sqlSession.update("memberMapper.deleteMember", userId);
	}
	
	public int idCheck(SqlSessionTemplate sqlSession, String checkId) {
		return sqlSession.selectOne("memberMapper.idCheck", checkId);
	}

	public Object insertSecret(SqlSessionTemplate sqlSession, CertVO certVo) {
		
		return sqlSession.insert("memberMapper.insertSecret", certVo);
	}

	public boolean vaildate(SqlSessionTemplate sqlSession, CertVO certVo) {
		CertVO result = sqlSession.selectOne("memberMapper.vaildate", certVo);
		return result != null;
	}

	public void deleteCert(SqlSessionTemplate sqlSession, CertVO certVo) {
		sqlSession.delete("memberMapper.deleteCert", certVo);
	}
}
