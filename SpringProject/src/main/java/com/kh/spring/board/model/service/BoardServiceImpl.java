package com.kh.spring.board.model.service;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.board.model.dao.BoardDao;
import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//게시글 총 개수 조회
	@Override
	public int selectListCount() {
		
		int listCount = boardDao.selectListCount(sqlSession);
		
		return listCount;
	}

	//게시글 목록 조회 메소드
	@Override
	public ArrayList<Board> selectList(PageInfo pi) {
		
		ArrayList<Board> bList = boardDao.selectList(sqlSession,pi);
		
		return bList;
	}

	@Override
	public int insertBoard(Board b) {
		int result = boardDao.insertBoard(b,sqlSession);
		return result;
	}

	//게시글 조회수 증가 메소드
	@Override
	public int increaseCount(int boardNo) {

		int result = boardDao.increaseCount(sqlSession,boardNo);
		return result;
	}

	//게시글 조회 메소드
	@Override
	public Board selectBoard(int boardNo) {
		
		Board b = boardDao.selectBoard(sqlSession,boardNo);
		return b;
	}

	//게시글 업데이트 메소드
	@Override
	public int updateBoard(Board b) {
		
		int result = boardDao.updateBoard(sqlSession,b);
		return result;
	}

	//게시글 삭제 메소드
	@Override
	public int deleteBoard(int boardNo) {
		
		int result = boardDao.deleteBoard(sqlSession,boardNo);
		return result;
	}

	//댓글 조회 메소드
	@Override
	public ArrayList<Reply> selectReply(int boardNo) {
		
		ArrayList<Reply> rList = boardDao.selectReply(sqlSession,boardNo);
		return rList;
	}

	//댓글 게시 메소드
	@Override
	public int insertReply(Reply r) {
		
		int result = boardDao.insertReply(sqlSession,r);
		return result;
	}

	//순위 리스트 조회
	@Override
	public ArrayList<Board> selectTopList(int rank) {
		
		ArrayList<Board> bList = boardDao.selectTopList(sqlSession,rank);
		return bList;
	}

}
