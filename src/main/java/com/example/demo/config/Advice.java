package com.example.demo.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.vo.EmpVO;

import jakarta.servlet.http.HttpSession;

@ControllerAdvice
// 모든 @Controller에서 공통으로 적용 가능한 
// 예외, 전역설정, 데이터 모델 등을 추가한다.
public class Advice {

	@ModelAttribute("userInfo")
	public EmpVO userInfo(HttpSession session) {
		return (EmpVO) session.getAttribute("userInfo");
	}
	
	@ExceptionHandler(LoginException.class)
	public RedirectView handleLogin(
			RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("error", "로그인이 필요합니다.");
		return new RedirectView("/member/login");
	}
	
	
	
	
	
	
}
