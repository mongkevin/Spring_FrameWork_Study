package com.kh.spring.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.spring.member.model.service.MemberService;
import com.kh.spring.member.model.vo.Member;

@Controller //Controller타입의 어노테이션을 붙여주면 빈 스캐닝을 통해서 자동으로 bean 등록을 해준다
public class MemberController {
	
//	private MemberService memberService = new MemberServiceImpl();
	
	/*
	 * 기존 객체 생성방식
	 * 객체간의 결합도가 높다 (소스 수정이 일어나면모두 바꿔야한다)
	 * 서비스가 동시에 많은 횟수가 요청되면 매번 객체가 생성 되어야한다.
	 * 
	 * *Spring의 DI (Dependency Injection)을 이용하는 방법
	 * 객체를 생성해서 주입한다(객체간의 결합도를 낮춤)
	 * new 라는 키워드 없이 선언문만 작성하여도 된다. 단, @Autowired라는 어노테이션을 작성하여야한다.
	 * */
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	/*
	 * Spring에서 파라미터를 받는 방법
	 * 1.HttpServletRequest를 이용해서 전달받기 (기존에 jsp/servlet 때 방식)
	 *   해당 메소드의 매개변수로 HttpServletRequest를 작성해두면
	 *   스프링 컨테이너가 해당 메소드를 호출할때 (실행) 자동으로 해당 객체를 생성하여 매개변수로 주입해준다.
	 * */
	
//	@RequestMapping("login.me")
//	public String loginMember(HttpServletRequest request) {
//		
//		String userId = request.getParameter("userId");
//		String userPwd = request.getParameter("userPwd");
//		
//		return "main";
//	}
	
	/*
	 * 2.@RequestParam 어노테이션을 이용하는 방법
	 *   request.getParameter("키")로 벨류를 얻는 역할을 해주는 어노테이션
	 *   value 속성의 값으로 jsp에서 작성했던 name 속성값을 넣어주면 해당 매개변수로 담아올 수 있다.
	 *   만약, 넘어온 값이 비어있다면 defaultValue를 설정하여 기본값을 넣어줄수도 있다.
	 * */
	
//	@RequestMapping("login.me")//만약 defaultValue를 지정해줄 필요없음 value= 를 붙힐 필요가 없다
//	public String loginMember(@RequestParam(value="userId",defaultValue="abc") String userId,
//							  @RequestParam("userPwd") String userPwd) {
//		System.out.println(userId);
//		System.out.println(userPwd);
//		return "main";
//	}
	
	/*
	 * 3.@RequestParam 어노테이션을 생략하는 방법
	 *   단, 매개변수명을 jsp의 name 속성값과 동일하게 맞춰주어야 한다
	 *   또한 defaultValue 속성을 사용할 수 없음
	 * */
	
//	@RequestMapping("login.me")//매개변수 넣어도 없으면 null로 뜬다
//	public String loginMember(String userId, String userPwd) {
//		System.out.println(userId);
//		System.out.println(userPwd);
//		
//		Member m = new Member();
//		m.setUserId(userId);
//		m.setUserPwd(userPwd);
//		
//		//Service에 m전달 및 요청
//		return "main";
//	}
	
	/*
	 * 4.커맨드 객체 방식
	 *   해당 메소드의 매개변수로
	 *   요청시 전달값을 담고자 하는 VO클래스 타입을 넣고
	 *   요청시 전달값의 키값(jsp의 name속성)을 VO클래스에 담고자하는 필드명으로 작성하면
	 *   스프링 컨테이너가 해당객체를 기본생성자로 생성 후 내부저긍로 setter메소드를 찾아
	 *   요청시 전달값을 해당 필드에 담아주게 된다.
	 *   이때 name과 필드명을 꼭 일치시켜야 한다.
	 *   
	 *   **요청 처리 후 응답데이터를 담고 응답페이지로 포워딩 또는 재요청하는 방법
	 *   
	 *   1. 스프링에서 제공하는 Model 객체를 이용하는 방법
	 *      포워딩할 응답뷰로 전달하고자 하는 데이터를 맵형식(key-value)으로 담을 수 있는 영역
	 *      Modal객체는 requestScope
	 *      이때 데이터를 추가하는 메소드는 setAttribute가 아닌 addAttribute이다. 
	 * */
	
//	@RequestMapping("login.me")
//	public String loginMember(Member m, HttpSession session, Model model) {
//		//name값과 필드값을 일치시켜줘야한다.
//		Member loginUser = memberService.loginMember(m);
//		
//		if(loginUser==null) {//로그인 실패
//			//실패하면 에러페이지로 에러메세지와 함께 전달하기
//			model.addAttribute("errorMsg","로그인 실패");
//			//포워딩 방식으로 보낼때 (파일경로를 포함한 파일명 제시)
//			//servlet-context.xml의 주소를 자동완성 등록시켜주는 bean : viewResolver
//			//-접두어: /WEB-INF/views/
//			//-접미어: .jsp
//			
//			// /WEB-INF/views/common/errorPage.jsp
//			return "common/errorPage";
//		}else {//로그인 성공
//			//세션에 로그인 정보 담고 메인으로 돌려주기
//			//리다이렉트 요청하는 법:"redirect:/경로"; -contextPath뒤에 붙는 /를 의미한다 즉 메이네이지
//			session.setAttribute("loginUser", loginUser);
//			return "redirect:/"; // http://localhost:8888/spring/
//		}
//		
//	}
	
	/*
	 * 2.스프링에서 제공하는 ModelAndView 객체를 사용하는 방법
	 *   Model은 데이터를 key-value 세트로 담을 수 있는 공간이라고 한다면
	 *   View는 응답뷰에 대한 정보를 담을 수 있는 공간
	 *   이 경우에는 반환타입이 String이 아니라 ModelAndView 타입으로 return한다.
	 *   
	 *   Model과 View가 결합된 형태
	 *   Model객체는 단독사용이 가능하지만 View는 불가능하다.
	 * */
	@RequestMapping("login.me")
	public ModelAndView loginMember(Member m, HttpSession session, ModelAndView mv) {
		
		//암호화 사용 전
//		Member loginUser = memberService.loginMember(m);
//		if(loginUser==null) {
//			//model.addAttribute("errorMsg","로그인 실패")
//			mv.addObject("errorMsg","로그인 실패");
//			//return "common/errorPage";
//			mv.setViewName("common/errorPage");
//		}else {
//			session.setAttribute("loginUser", loginUser);
//			mv.setViewName("redirect:/");
//		}
//		return mv;
		
		//암호화 사용 후 (입력받은 아이디로 회원정보 조회 후 해당 비밀번호 (암호)와 입력받은 비밀번호(평문)
		//일치하는지 확인
		Member loginUser = memberService.loginMember(m);
		//loginUser는 아이디만 가지고 조회한 회원 정보
		//loginUser의 userPwd는 암호화된 비밀번호
		
		//BcryptPasswordEncoder객체의 matches라는 메소드를 이용하여 암호화된 문자를 복호화하여 비교해줌
		//matches(평문,암호문)을 작서하면 내부적으로 복호화작업을 통해 두 값이 일치하는 값인지 판변해줌(일치하면 true/일치하지 않으면 false)
		
//		System.out.println(bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd()));
		
		if(loginUser!=null&&bcryptPasswordEncoder.matches(m.getUserPwd(), loginUser.getUserPwd())){
			session.setAttribute("loginUser", loginUser);
			mv.setViewName("redirect:/");
		}else {
			mv.addObject("errorMsg","로그인 실패");
			mv.setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	//로그아웃
	@RequestMapping("logout.me")
	public String logoutMember(HttpSession session) {
		session.removeAttribute("loginUser");
		return "redirect:/";
	}
	
	//회원가입 폼 이동 메소드
	@RequestMapping("enrollForm.me")
	public String enrollForm() {
		return "member/memberEnroll";
	}
	
	//회원가입 메소드
	@RequestMapping("insert.me")
	public String insertMember(Member m, Model model, HttpSession session) {
		
		//1.한글 깨짐 문제 발생 - web.xml파일에 스프링에서 제공하는 인코딩 필터를 등록하여 사용
		
		//2.나이를 입력하지 않았을때 int 자료형에 빈문자열이 들어가 자료형이 맞지않아 오류발생
		//Member객체 age필드를 String으로 변경처리
		
		//3.비밀번호가 사용자가 입력한 값 그래도 db에 등록(암호화 필요)
		//->Bcrypt방식의 암호화를 통해 암호문으로 변경
		// 1)스프링 시큐리티 모듈에서 제공(pom.xml에 추가)
		// 2)BCryptPasswordEncoder 클래스를 xml파일에 bean으로 등록
		// 3)web.xml에 spring-security.xml파일을 로딩할 수 있도록 작성
		
//		System.out.println("평문: "+m.getUserPwd());
		
		//암호화 작업(암호문 만들기)
		String encPwd = bcryptPasswordEncoder.encode(m.getUserPwd());
		
		System.out.println("암호화된 패스워드: "+encPwd);
		
		//회원가입 구현
		m.setUserPwd(encPwd);
		int result = memberService.insertMember(m);
		
		if(result>0) {
			//성공시 - 메인페이지로 url 재요청
			//성공 메세지 세션에 담아서 띄우기
			session.setAttribute("alertMsg", "회원가입 성공");
			return "redirect:/";
		}else {
			//실패시 -에러페이지로 에러메세지 담아서 포워딩하기
			model.addAttribute("errorMsg","회원가입실패");
			return "common/errorPage";
		}
	}
	
	//마이페이지로 이동
	@RequestMapping("mypage.me")
	public String myPage() {
		
		return "member/myPage";
	}
	
	//정보 수정
	@RequestMapping("update.me")
	public ModelAndView updateMember(Member m,ModelAndView mv, HttpSession session) {
		
		int result = memberService.updateMember(m);
		
		if(result>0) { //수정 성공
			//DB에 수정이된 데이터로 세션에 담겨있는 loginUser정보를 갱신하여야 한다.
			Member updateUser = memberService.loginMember(m);
			session.setAttribute("loginUser", updateUser);
			session.setAttribute("alertMsg", "정보수정 완료");
			mv.setViewName("redirect:mypage.me");
		}else { //수정 실패
//			mv.addObject("errorMsg","정보수정 실패");
//			mv.setViewName("common/errorPage");
			mv.addObject("errorMsg","정보수정 실패").setViewName("common/errorPage");
		}
		
		return mv;
	}
	
	//회원탈퇴 메소드
	@RequestMapping("delete.me")
	public ModelAndView deleteMember(String userId, String userPwd, ModelAndView mv, HttpSession session) {
		
		//userPwd는 회원탈퇴 요청시에 사용자에게 입력받은 평문
		//loginUser userPwd필드값(암모화되어있는 비밀번호)
		String encPwd = ((Member)session.getAttribute("loginUser")).getUserPwd();
		
		//입력받은 비밀번호와 로그인된 정보 비밀번호와 (복호화작업을 통해) 일치하는지 판별
		if(bcryptPasswordEncoder.matches(userPwd, encPwd)) {
			
			//일치했을때
			int result = memberService.deleteMember(userId);
			
			if(result>0) {
				//탈퇴처리 성공
				session.setAttribute("alertMsg", "탈퇴성공");
				session.removeAttribute("loginUser");
				mv.setViewName("redirect:/");
			}else {
				mv.addObject("errorMsg","회원탈퇴 실패").setViewName("common/errorPage");
			}
		}else {
			session.setAttribute("alertMsg", "비밀번호가 틀립니다.");
			mv.setViewName("redirect:mypage.me");
		}
		
		return mv;
	}
	
	//아이디 중복 확인 ajax
	@ResponseBody
	@RequestMapping("idCheck.me")
	public String idCheck(String checkId) {
		
		int count = memberService.idCheck(checkId);
//		String result = "";
//		if(count>0) { //조회결과가 있다(중복)
//			result = "NNNNN";
//		}else { //조회결과 없음(사용가능)
//			result = "NNNNY";
//		}
		System.out.println(count);
		return (count>0)?"NNNNN":"NNNNY";
	}
}
