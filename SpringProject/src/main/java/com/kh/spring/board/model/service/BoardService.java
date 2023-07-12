package com.kh.spring.board.model.service;

import java.util.ArrayList;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

public interface BoardService {

	//게시글 리스트 조회 서비스 + 페이징 처리
	
	//게시글 총 개수
	int selectListCount();
	
	//게시글 리스트 조회
	ArrayList<Board> selectList(PageInfo pi);
	
	//게시글 작성하기 서비스
	int insertBoard(Board b);
	
	//게시글 상세조회
	
	
	//게시글 조회수 증가 메소드
	int increaseCount(int boardNo);
	
	//게시글 조회 메소드
	Board selectBoard(int boardNo);
	
	//게시글 수정
	int updateBoard(Board b);
	
	//게시글 삭제
	int deleteBoard(int boardNo);

	//게시글 댓글 조회
	ArrayList<Reply> selectReply(int boardNo);

	int insertReply(Reply r);

	ArrayList<Board> selectTopList(int rank);

	
}
