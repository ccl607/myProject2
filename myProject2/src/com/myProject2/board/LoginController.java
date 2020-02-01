package com.myProject2.board;

import java.util.*;
import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


//가상 URL 주소로 접속하여 호출되는 메소드를 소유한 컨트롤러 클래스를 선언
//@Controller를 붙임으로써 컨트롤러 클래스임을 지정한다.

@Controller
public class LoginController {
	
	@Autowired
	//private LoginService loginService;	//속성변수 loginService를 선언하고 @Autowired에 의해 
										//loginService 인터페이스를 구현한 클래스를 찾아 객체화시키고 이 객체의 메모리위치주소값을 저장한다. 
										//구현한 클래스명을 몰라도 상관없으며, 1개 존재하기만 하면 된다.
	
	//가상주소 /z_spring/loginForm.do로 접근하면 호출되는 메소드 선언
	@RequestMapping( value="/loginForm.do")
	public ModelAndView loginForm() {
		//ModelAndView 객체 생성
		//ModelAndView 객체에 호출할 JSP 페이지명을 저장
		//ModelAndView 객체 리턴
		ModelAndView mav = new ModelAndView();
		mav.setViewName("loginForm.jsp");
		
		return mav;
	}
	
	@RequestMapping( value="/goMainForm.do")
	public ModelAndView goMainForm() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("main.jsp");
		
		return mav;
	}
	
	//가상주소 /z_spring/loginProc.do로 접근하면 호출되는 메소드 선언
	//메소드 호출 후 ModelAndView 객체를 리턴하지 않고 아이디와 암호의 존재 개수를 리턴하도록 설정
	/*@RequestMapping( value="/loginProc.do"	//접속하는 클라이언트의 URL 주소 설정 loginProc.do
			, method=RequestMethod.POST		//접속하는 클라이언트의 파라미터값 전송법 설정
			, produces = "application/json;charset=UTF-8"	//응답할 데이터 종류 설정
	)
	
	@ResponseBody
	public int loginProc(
			@RequestParam(value="admin_id") String admin_id,
			@RequestParam(value="pwd") String pwd
			//@RequestParam Map<String, String> paramsMap
			//String admin_id
			//, String pwd
			) {
		
		//매개변수에 저장된 파라미터값(아이디, 암호)를 HashMap에 저장하기
		//이렇게 한 군데에 모으는 이유는 서비스 클래스에 전달할 때 하나로 단일화하기 위함이다.
		System.out.println("로그인 컨트롤러 성공");
		Map<String, String> map = new HashMap<String, String>();
		map.put("admin_id", admin_id);
		map.put("pwd", pwd);
		
		int admin_idCnt = 0;		//로그인 아이디, 암호의 존재 개수를 저장하는 변수 선언하기
		//서비스 클래스인 LoginServiceImpl 객체의 메소드를 호출하여 admin이라는 테이블에 존재하는 로그인 아이디, 암호의 개수를 얻기
		
		//LoginService loginServiceImpl = new LoginServiceImpl9999();
		//admin_idCnt = loginServiceImpl.getAdminCnt(map);
		admin_idCnt = this.loginService.getAdminCnt(map);
		
		//ModelAndView 객체 생성
		//ModelAndView 객체에 호출할 JSP 페이지명을 저장
		//ModelAndView 객체 리턴
		//ModelAndView mav = new ModelAndView();
		//mav.setViewName("loginProc.jsp");
		//System.out.println("admin_idCnt=>"+admin_idCnt);
		return admin_idCnt;		//admin 테이블에 존재하는 로그인 아이디의 존재 개수를 리턴하기	
	}*/
	
	
	@RequestMapping( value="/goJoinMemberPage.do")
	public ModelAndView goJoinMemberPage() {
		//ModelAndView 객체 생성
		//ModelAndView 객체에 호출할 JSP 페이지명을 저장
		//ModelAndView 객체 리턴
		ModelAndView mav = new ModelAndView();
		mav.setViewName("joinMember.jsp");
		
		return mav;
	}
}
