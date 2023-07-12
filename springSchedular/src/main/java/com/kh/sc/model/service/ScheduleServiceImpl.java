package com.kh.sc.model.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.kh.sc.model.vo.Category;

import lombok.extern.slf4j.Slf4j;

//@Service
@Slf4j
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private SqlSession sqlSession;
	
	//root-context와 servlet-context에 어노테이션을 검색할 순서를 설정을 해야한다
	@Override
//	@Scheduled(initialDelay = 1000,fixedDelay = 1000)
//	@Scheduled(cron = "* * * * * *")//매초마다 실행해라 //크론표현식 검색하면 나온다
//	@Scheduled(cron = "*/2 * * * * *") //매 2초마다
//	@Scheduled (cron = "10-20 * * * * *") //매 10~20초 사이만
//	@Scheduled(cron = "0 * * * * *")//매분 0초마다
	//매시 정각마다  (cron = "0 0 * * * *")
	//매일 자정마다  (cron = "0 0 0 * * *")
	//매일 아침 6시마다  (cron = "0 0 6 * * *")
	//매월 1일 아침 7시마다  (cron = "0 0 7 1 * *")
	//@Scheduled(fixedDelay=1000)
	public void oneSecond() {
		
		int ran = (int)(Math.random()*99999)+1;
		System.out.println(ran);
		Category category = Category.builder().categoryNo(ran).categoryName("물고기").build();
		sqlSession.insert("testMapper.insertCategory",category);
		log.debug("스케줄러 테스트");		
	}
//	
//	@Scheduled(fixedDelay=1000)
//	public void ts() {
//		
//	}
}
