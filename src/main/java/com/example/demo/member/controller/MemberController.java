package com.example.demo.member.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.EmpService;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.LoginRequest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member")
@Slf4j
//@AllArgsConstructor
public class MemberController {
	
	@Autowired
	private EmpService empService;
	
//	private final EmpService empService2;
	
//	public MemberController(EmpService empService2) {
//		super();
//		this.empService2 = empService2;
//	}

	@GetMapping("/login")
	// @RequestMapping(value = "/login", method=RequestMethod.GET)
	public ModelAndView memberLogin() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("userInfo", new EmpVO());
		return mav;
	}
	
	@PostMapping("/loginProc")
	//@RequestMapping(value = "/loginProc", method=RequestMethod.POST)
	public ModelAndView memberLoginProc(
				@ModelAttribute LoginRequest loginRequest,
				HttpServletRequest request
			) {
		ModelAndView mav = new ModelAndView();
		// 로그인 처리 
		// 1. DB에 해당 사용자가 있는지 체크
		EmpVO result = empService.userLogin(loginRequest);
		
		if (result != null) {
			log.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("userInfo", result);
			// board/write > /member/login > board/write
			// index > 로그인 페이지 이동 > 로그인 redirectUrl x > 첫페이지로 이동
			String redirectUrl = (String) session.getAttribute("redirectUrl");
			if (redirectUrl != null) {
				log.info(redirectUrl);
				// 리다이렉트
				mav.setViewName("redirect:" + redirectUrl);
				// 리다이렉트 후 세션에서 redirectUrl 삭제
				session.removeAttribute("redirectUrl");
			} else {
				log.info("redirectUrl 값이 없으면 랜딩페이지로 이동");
				// redirectUrl 값이 없으면 랜딩페이지로 이동
				mav.setViewName("redirect:/");
			}
		} else {
			log.info("로그인 실패");
			// 로그인 페이지 > 로그인 시도 실패 > 로그인 페이지
			mav.setViewName("forward:/member/login");
		}
		
		// 2. 있으면 session 생성
		// 3. redirectUrl 값이 있으면 로그인 처리 후 해당 경로로 redirect
		//    만약 값이 없으면 랜딩페이지로 이동
		//    로그인 실패시 로그인페이지로 다시 이동
		System.out.println(loginRequest.getUsername() + " " + loginRequest.getPassword());
		return mav;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	
}
