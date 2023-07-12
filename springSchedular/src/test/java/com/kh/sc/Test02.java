package com.kh.sc;

import javax.swing.JOptionPane;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test02 {

	public static void main(String[] args) {
		//목표: 앞 예제의 작업을 병렬로 처리하기
		//-main을 제외한 별도의 Thread가 필요하다.
		
		
		//스레드 생성 및 구동
		//스레드 생성
		Thread t = new Thread() {
			@Override
			public void run() {
				//별도의 스레드로 동작할 내용 입력
				//1부터 100까지 출력
				for(int i=1; i<101; i++) {
					
					log.debug("i={}",i);
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}; 
		t.setDaemon(true); //데몬 스레드 (종속계약) -메인이 종료되면 같이 종료(모든 스레드에 설정)
		//구동
		t.start();
		
		//알림창
		JOptionPane.showMessageDialog(null, "안녕하세요");
		
	}
}
