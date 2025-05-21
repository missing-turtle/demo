package com.example.demo.service;

import java.util.List;

import com.example.demo.vo.EmpVO;
import com.example.demo.vo.LoginRequest;

public interface EmpService {
	
	List<EmpVO> selectEmp();
	
	EmpVO userLogin(LoginRequest loginRequest);
	
}
