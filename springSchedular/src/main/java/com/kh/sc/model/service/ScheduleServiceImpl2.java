package com.kh.sc.model.service;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Service
@Slf4j
@EnableAsync // 비동기처리 가능 어노테이션
public class ScheduleServiceImpl2 implements ScheduleService {

	
	//root-context와 servlet-context에 어노테이션을 검색할 순서를 설정을 해야한다
	@Override
	@Scheduled(initialDelay = 1000,fixedDelay = 1000)
	@Async
	public void oneSecond() {
		try {
			log.debug("스케줄러1 테스트 시작 {}",Thread.currentThread().getName());
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("스케줄러1 테스트 종료 {}",Thread.currentThread().getName());		
	}
	
	@Scheduled(initialDelay = 1000,fixedDelay = 1000)
	public void ts() {
		log.debug("스케줄러2 실행 {}",Thread.currentThread().getName());
	}
}
