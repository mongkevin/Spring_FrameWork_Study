package com.kh.spring.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.member.model.dao.MemberDao;
import com.kh.spring.member.model.vo.Member;

@Service
//@Component로 등록시켜도 되지만 각자의 역할에 맞춰서 좀 더 구체화된 어노테이션 부여
public class MemberServiceImpl implements MemberService{

//	private memberDao = new MemberDao();
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member loginMember(Member m) {
		
		Member loginUser = memberDao.loginMember(sqlSession,m);
		
		return loginUser;
	}

	//회원가입 메소드
	@Override
	public int insertMember(Member m) {
		
		int result = memberDao.insertMember(sqlSession,m);
		
		return result;
	}

	//회원정보 수정 메소드
	@Override
	public int updateMember(Member m) {
		
		int result = memberDao.updateMember(sqlSession,m);
		
		return result;
	}

	//회원 탈퇴 메소드
	@Override
	public int deleteMember(String userId) {
		
		int result = memberDao.deleteMember(sqlSession,userId);
		
		return result;
	}

	//아이디 중복 체크
	@Override
	public int idCheck(String checkId) {
		
		int count = memberDao.idCheck(sqlSession,checkId);
		return count;
	}

}
