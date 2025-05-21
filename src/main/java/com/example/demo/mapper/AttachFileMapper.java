package com.example.demo.mapper;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.AttachFileDetailVO;

@Mapper
public interface AttachFileMapper {

	void insertFileDetail(AttachFileDetailVO vo);

	Optional<AttachFileDetailVO> findByFileName(String fileName);
}
