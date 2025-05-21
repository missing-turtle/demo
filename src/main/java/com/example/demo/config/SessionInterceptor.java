package com.example.demo.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.EmpVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("세션인터셉터");
		// 1. 세션 체크 (세션이 없으면 null 반환)
		HttpSession session = request.getSession(false);
		// 2. 세션이 존재하고, 특정한 키의 속성이 있으면 로그인 된 상태로 판단
		if (session != null) {
			EmpVO userInfo = (EmpVO) session.getAttribute("userInfo");
			if (userInfo != null) {
				return true; // 요청을 계속 진행한다. (즉... 컨트롤러로 넘어간다)
			}
		}
		// 3. 세션이 없는 경우 : 빈(empty) 세션을 생성 후 로그인 페이지로 이동시키기
		String requestedUrl = request.getRequestURI();
		session = request.getSession();
		session.setAttribute("redirectUrl", requestedUrl);
		
		// 4. 로그인 페이지로 이동
		response.sendRedirect(request.getContextPath() + "/member/login");
		return false; // 요청을 차단함 (컨트롤러로 넘어가지 않음)
	}

}
