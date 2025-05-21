package com.example.demo.mapper;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.SearchHelper;

@Mapper
public interface BoardMapper {

	List<BoardVO> selectList(SearchHelper searchHelper);
	
	int selectListCount(SearchHelper searchHelper);
	
	void insertBoard(BoardVO boardVO);
	
	Optional<BoardVO> selectBoard(Long boardNo);

	void updateBoard(BoardVO boardVO);

	void deleteBoard(Long boardNo);
	
}
