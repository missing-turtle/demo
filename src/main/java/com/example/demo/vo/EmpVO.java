package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EmpVO {
	private int empNo;
	private String eName;
	private int deptNo;
	
	@Builder	
	public EmpVO(int empNo, String eName, int deptNo) {
		super();
		this.empNo = empNo;
		this.eName = eName;
		this.deptNo = deptNo;
	}
	
	
}
