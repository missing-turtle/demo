package com.example.demo.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.EmpMapper;
import com.example.demo.service.EmpService;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.LoginRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {
	
	// private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmpMapper empMapper;
	
	@Override
	public List<EmpVO> selectEmp() {
		List<EmpVO> list = empMapper.selectEmp();
		log.info("jsdfsijdfij");
		EmpVO vo = EmpVO.builder()
				.deptNo(1)
				.empNo(0)
				.eName("sdsdsd")
				.build();
		
		
		return list;
	}

	@Override
	public EmpVO userLogin(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return empMapper.userLogin(loginRequest);
	}

}
