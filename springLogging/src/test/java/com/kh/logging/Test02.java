package com.kh.logging;

import lombok.extern.slf4j.Slf4j;

//이 클래스에서 Loggin을 하고 싶은 경우 어노테이션 (self4j 추가)
@Slf4j
public class Test02 {

	//Junit : 자바 프로그램밍 언어용 유닛 테스트 프레임워크
	//TDD : 테스트 주도 개발 (Test Driven Development)
	//가장 작은 단위의 기능을 하는 코드들을 개발 후 실행이 되는지 개별 테스트가 필요할때 사용한다.
	@org.junit.Test
	public void test2() {
		log.debug("디버그");
		log.info("인포");
		log.warn("경고");
		log.error("에러");
		
	}
}
