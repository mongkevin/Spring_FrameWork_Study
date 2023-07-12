package com.kh.aop.aoptest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.kh.aop.model.vo.Category;

import lombok.extern.slf4j.Slf4j;

//특정 대상의 소요시간을 측정하기 위한 간섭 객체
@Slf4j
@Aspect
public class TimerAspect {

	/*
	 * 간섭 메소드(Advice)
	 * -특수한 경우가 아니라면 메소드의 형태가 정해져있다.
	 * -누구를 어떤 시점에 간섭할지는 어노테이션으로 설정한다.
	 * */
//	@Before("target(com.kh.aop.model.dao.CategoryDaoImpl)")
//	public void before() {
//		log.debug("before 실행");
//	}
//	
//	@After("target(com.kh.aop.model.dao.CategoryDaoImpl)")
//	public void after() {
//		log.debug("after 실행");
//	}
	
//	@Before("target(com.kh.aop.model.dao.CategoryDaoImpl)")
//	public void before(JoinPoint jp) {
//		log.debug("메소드 이름: {}",jp.getSignature().getName());//메소드 이름
//		log.debug(jp.getSignature().toString()); //주소
//		log.debug(jp.getSignature().getDeclaringTypeName());//클래스의 풀네임이 나온다
//		log.debug("before 실행");
//	}
//	
//	@After("target(com.kh.aop.model.dao.CategoryDaoImpl)")
//	public void complete(JoinPoint jp) {
//		log.debug("메소드 이름: {}",jp.getSignature().getName());//메소드 이름
//		log.debug(jp.getSignature().toString()); //주소
//		log.debug(jp.getSignature().getDeclaringTypeName());//클래스의 풀네임이 나온다
//		log.debug("after 실행");
//	}
	
//	@AfterReturning(pointcut = "target(com.kh.aop.model.dao.CategoryDaoImpl)",
//					returning = "returnObj")
//	public void complete(JoinPoint jp, Object returnObj){
//		System.out.println("joinPoint : "+jp); //execution(ArrayList com.kh.aop.model.dao.CategoryDao.selectCategory())
//		System.out.println("메소드 : "+jp.getSignature().getName());// selectCategory
//		System.out.println("리턴 : ");
//		System.out.println(returnObj);//[Category(category_no=1, category_name=전자기기)]
//		
//		ArrayList<Category> list = (ArrayList<Category>)returnObj;
//		
//		for(Category c:list) {
//			System.out.println(c);//Category(category_no=1, category_name=전자기기)
//		}
//	}
	
//	@AfterThrowing(pointcut = "target(com.kh.aop.model.dao.CategoryDaoImpl)",
//				   throwing = "excepObj")
//	public void afterThrowing(JoinPoint jp,Exception excepObj) {
//		System.out.println("메소드 호출 : "+jp.getSignature().getName());
//		System.out.println("예외 메세지 : "+excepObj); //예외 상황에서 뜬다
//	}

	//전반적인 간섭을 수행하는 간섭 메소드(Advice)
	//메소드형태가 지정되어있다.
	/*
	 * pointcut 정의 표현식
	 * -within : 특정 패키지 or 클래스의 모든 메소드 지정
	 * -target : 특정 인터페이스와 그의 자식 클래스의 메소드를 지정 (풀경로작성 / 만약 인터페이스면 구현체도 전부 적용)
	 * -args : 특정 매개변수 형태를 갖는 모든 메소드를 지정
	 * -this : 특정 인터페이스를 구현하는 프록시 객체를 지정
	 * -execution : 표현식으로 형태를 지정하여 간섭
	 * */
	
//	@Around("target(com.kh.aop.model.dao.CategoryDao)")
//	@Around("within(com.kh.aop.model.dao.*)")//특정 패키지
//	@Around("within(com.kh.aop.model.dao.CategoryDaoImpl)")//클래스의 모든 메소드
//	@Around("execution(public * com.kh.aop.model.*.*()")//원하는 시점과 메소드 설정
//	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
//		long start = System.currentTimeMillis();//@Before
//		Object result = joinPoint.proceed(); //대상을 실행시키는 명령
//		long finish = System.currentTimeMillis();//@After
//		long time = finish-start;
//		log.debug("time={} result={} joinPoint={}",time,result,joinPoint.getSignature().toString());
//		//time=624 result=[Category(category_no=1, category_name=전자기기)] joinPoint=ArrayList com.kh.aop.model.dao.CategoryDao.selectCategory()
//		return result;
//	}
}
