package com.kh.spring.board.model.dao;

import java.util.ArrayList;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.kh.spring.board.model.vo.Board;
import com.kh.spring.board.model.vo.Reply;
import com.kh.spring.common.model.vo.PageInfo;

@Repository
public class BoardDao {

	//게시글 총 개수 조회
	public int selectListCount(SqlSession sqlSession) {
		
		return sqlSession.selectOne("boardMapper.selectListCount");
	}

	//게시글 목록 조회 메소드
	public ArrayList<Board> selectList(SqlSession sqlSession, PageInfo pi) {
		
		int limit = pi.getBoardLimit();
		int offset = (pi.getCurrentPage()-1)*limit;
		RowBounds rowBounds = new RowBounds(offset,limit);
		return (ArrayList)sqlSession.selectList("boardMapper.selectList",null,rowBounds);
	}

	//게시글 조회 메소드
	public Board selectBoard(SqlSessionTemplate sqlSession, int boardNo) {
		
		return sqlSession.selectOne("boardMapper.selectBoard",boardNo);
	}

	//게시글 조회 증가
	public int increaseCount(SqlSessionTemplate sqlSession, int boardNo) {
		
		return sqlSession.update("boardMapper.increaseCount",boardNo);
	}

	//게시글 작성
	public int insertBoard(Board b, SqlSessionTemplate sqlSession) {
		
		return sqlSession.insert("boardMapper.insertBoard",b);
	}

	//게시글 수정
	public int updateBoard(SqlSessionTemplate sqlSession, Board b) {
		
		return sqlSession.update("boardMapper.updateBoard",b);
	}

	public int deleteBoard(SqlSessionTemplate sqlSession, int boardNo) {
		
		return sqlSession.delete("boardMapper.deleteBoard",boardNo);
	}

	//댓글 조회 메소드
	public ArrayList<Reply> selectReply(SqlSessionTemplate sqlSession, int boardNo) {
		
		return (ArrayList)sqlSession.selectList("boardMapper.selectReply", boardNo);
	}

	//댓글 게시 메소드
	public int insertReply(SqlSessionTemplate sqlSession, Reply r) {
		
		return sqlSession.insert("boardMapper.insertReply",r);
	}

	public ArrayList<Board> selectTopList(SqlSessionTemplate sqlSession, int rank) {
		RowBounds rowBounds = new RowBounds(0,rank);
		return (ArrayList)sqlSession.selectList("boardMapper.selectTopList",null,rowBounds);
	}

}
