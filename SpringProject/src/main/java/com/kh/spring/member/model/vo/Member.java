package com.kh.spring.member.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Lombok (롬북)
 * -자동 코드 생성 라이브러리
 * 반복되는 getter,setter,toString 등의 메소드 작성 코드를 줄여주는 코드 라이브러리 
 * 
 * Lombok 사용시 주의 사항
 * -uName,bTitle과 같이 앞글자가 외자인 필드명은 만들지 말것. 필드명 작성시 적어도 소문자 두글자 이상으로 시작할 것
 * -EL표기법을 사용시 내부적으로 getter메소드를 찾을때 getuName(),getbTitle()이라는 메소드를 호출하게 됨
 *  (우리의 명명규칙에 의하면 getUName(),getBTitle()이 호출되어야하기 때문에 오류가 발생한다.)
 * */

@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든 필드를 매개변수로 가지는 생성자
@Setter //Setter 메소드
@Getter //Getter 메소드
@ToString // toString 메소드
@EqualsAndHashCode //equals와 hashcode 메소드
@Data // getter,setter,argsCons~,toString,equalsand~와 같이 위에있는 이노테이션 다 포함한 이노테이션
public class Member {
	
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String gender;
	private String age; //int 에서 String으로 변환
	private String phone;
	private String address;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
}
