package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.vo.BoardVO;
import com.example.demo.vo.SearchHelper;

public interface BoardService {
	List<BoardVO> selectList(SearchHelper searchHelper);
	
	int selectListCount(SearchHelper searchHelper);
	
	void insertBoard(BoardVO boardVO);
	
	Optional<BoardVO> selectBoard(Long boardNo);

	void updateBoard(BoardVO boardVO);

	void deleteBoard(Long boardNo);
	
}
