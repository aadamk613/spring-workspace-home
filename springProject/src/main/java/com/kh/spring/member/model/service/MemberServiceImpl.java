package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.CertVO;
import com.kh.spring.member.model.vo.Member;

// @Componet == bean으로 등록하겠다.
@Service // component보다 더 구체화해서 Service Bean으로 등록하겠다.
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private SqlSessionTemplate sqlSession; // 기존의 mybatis의 SqlSession 대체
	
	@Override
	public Member loginMember(Member m) {
		// Member loginMember = memberDao.loginMember(sqlSession, m);
		// SqlSessionTemplate객체를 bean으로 등록 @Autowired
		// 스프링이 사용 후 자동으로 객체를 알아서 반납시켜주기 때문에
		// close()를 호출하지 않음!!
		return memberDao.loginMember(sqlSession, m);
	}
	@Override
	public int insertMember(Member m) {
		// 커넥션 만들기
		// dao호출
		// 트랜잭션 처리
		// 자원반납
		// 리턴
		// SqlSessionTemplate객체가 자동으로 commit해줌
		return memberDao.insertMember(sqlSession, m);
	}

	@Override
	public int updateMember(Member m) {
		return memberDao.updateMember(sqlSession, m);
	}

	@Override
	public int deleteMember(String userId) {
		return memberDao.deleteMember(sqlSession, userId);
	}
	@Override
	public int idCheck(String checkId) {
		return memberDao.idCheck(sqlSession, checkId);
	}
	@Override
	public void sendMail(CertVO certVo) {
		memberDao.insertSecret(sqlSession, certVo);
	}
	@Override
	public boolean validate(CertVO certVo) {
		boolean result = memberDao.vaildate(sqlSession, certVo);
		if(result != false) {
			memberDao.deleteCert(sqlSession, certVo);
		}
		return result;
	}
	
	

}
