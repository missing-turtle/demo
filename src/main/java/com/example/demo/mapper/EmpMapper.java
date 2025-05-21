package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.EmpVO;
import com.example.demo.vo.LoginRequest;

@Mapper
public interface EmpMapper {
	
	List<EmpVO> selectEmp();
	
	EmpVO userLogin(LoginRequest loginRequest);
	
}
