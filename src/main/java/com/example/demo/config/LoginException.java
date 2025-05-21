package com.example.demo.config;

public class LoginException extends RuntimeException {
	
	public LoginException() {
		super("로그인이 필요합니다.");
	}
	
	public LoginException(String message) {
		super(message);
	}
	
}
