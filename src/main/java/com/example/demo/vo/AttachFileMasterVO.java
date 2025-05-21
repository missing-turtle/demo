package com.example.demo.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AttachFileMasterVO {

	private Long fileId;
	private String creator;
	private LocalDateTime createTime;
	
}
