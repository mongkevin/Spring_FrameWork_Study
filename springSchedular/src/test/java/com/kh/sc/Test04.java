package com.kh.sc;

import com.kh.sc.model.vo.ATM;

public class Test04 {
	//ATM기계를 하나 만들어 3명(Thread)에게 이용하도록 지시해보자
	
	public static void main(String[] args) {
		
		ATM atm = new ATM();
		
		Thread person1 = new Thread() {
			@Override
			public void run() {
				atm.getMoney();
			};
		};
		
		Thread person2 = new Thread() {
			@Override
			public void run() {
				atm.getMoney();
			};
		};
		
		Thread person3 = new Thread() {
			@Override
			public void run() {
				atm.getMoney();
			};
		};
		
		person1.start();
		person2.start();
		person3.start();
	}
	
}
