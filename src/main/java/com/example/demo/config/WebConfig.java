package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	/**
	 * Bean : 스프링 컨테이너에 의해 생성되고 관리되는 객체
	 * 의존성 주입 (DI)을 통해 객체 간 결합도를 낮춘다.
	 * 객체의 생명 주기를 스프링이 관리준다.
	 * 각 설정은 xml, 어노테이션, 자바코드로 설정 가능
	 */
	private final SessionInterceptor sessionInterceptor;
	public WebConfig(SessionInterceptor sessionInterceptor) {
		this.sessionInterceptor = sessionInterceptor;
	}
	
	// 세션을 체크해야하는 URL, 체크하지 말아야하는 URL
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 도메인/admin/*********, /member/info
		registry.addInterceptor(sessionInterceptor)
				.addPathPatterns("/admin/**")
				.addPathPatterns("/member/info")
				.addPathPatterns("/board/write", "/board/save");
	}
	
}
