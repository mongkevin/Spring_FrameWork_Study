package com.kh.spring.member.model.service;

import com.kh.spring.member.model.vo.Member;

public interface MemberService {

	//로그인용 메소드 (select)
	Member loginMember(Member m);

	//회원가입
	int insertMember(Member m);
	
	//회원정보 수정
	int updateMember(Member m);
	
	//회원 탈퇴
	int deleteMember(String userid);

	//아이디 중복체크
	int idCheck(String checkId);
	
}
