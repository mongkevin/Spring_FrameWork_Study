package com.kh.sc.model.vo;

public class ATM {
	private long money = 10000000000L;
	
	//synchronized가 붙으면 메소드 동기화(동시 접근 불가)
	public synchronized void getMoney() {
		System.out.println("인출 시작");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("인출 끝");
	}
}
