package com.kh.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {

	public static void main(String[] args) {

//		Logger logger = LoggerFactory.getLogger(Test.class);
//		
//		//log4j.xml 에서 
//		//<!-- Application Loggers -->
//		//<logger name="com.kh.logging">
//		//	<level value="info" />
//		//</logger>
//		//이부분 벨류값을 설정하면 이 보다 중요한 값들까지만 보여준다.
//		logger.debug("디버그 debug");
//		logger.info("정보 info");
//		logger.warn("경고 warn");
//		logger.error("에러 error");
		
		Member m = new Member();
		
		//생성자, setter
		m.setName("홍반장");
		m.setAge(20);
		m.setAddress("홍콩");
		m.setPhone("010-2423-1423");
		
		Member m2 = new Member("박과장",10,"서울특별시","010-4223-1423");
		
		//lombok을 사용한 데이터 주입
		Member bm = Member.builder().build();//기본 생성자
		//Member(name=null, age=0, address=null, phone=null)
		System.out.println(bm);
		
		Member bm2 = Member.builder().name("아이묭").age(21).address("일본").build();
		System.out.println(bm2);
		//Member(name=아이묭, age=21, address=일본, phone=null)
	}

}
