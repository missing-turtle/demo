package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.service.BoardService;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.SearchHelper;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class BoardServiceImple implements BoardService {
	
	private BoardMapper boardMapper;

	@Override
	public List<BoardVO> selectList(SearchHelper searchHelper) {
		// TODO Auto-generated method stub
		return boardMapper.selectList(searchHelper);
	}

	@Override
	public int selectListCount(SearchHelper searchHelper) {
		// TODO Auto-generated method stub
		return boardMapper.selectListCount(searchHelper);
	}

	@Override
	public void insertBoard(BoardVO boardVO) {
		// TODO Auto-generated method stub
		boardMapper.insertBoard(boardVO);
	}

	@Override
	public Optional<BoardVO> selectBoard(Long boardNo) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoard(boardNo);
	}

	@Override
	public void updateBoard(BoardVO boardVO) {
		// TODO Auto-generated method stub
		boardMapper.updateBoard(boardVO);
	}

	@Override
	public void deleteBoard(Long boardNo) {
		// TODO Auto-generated method stub
		boardMapper.deleteBoard(boardNo);
	}

}
