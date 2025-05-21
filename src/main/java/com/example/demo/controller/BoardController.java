package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.config.LoginException;
import com.example.demo.service.BoardService;
import com.example.demo.util.StringUtil;
import com.example.demo.vo.BoardVO;
import com.example.demo.vo.EmpVO;
import com.example.demo.vo.SearchHelper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
@AllArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@GetMapping("/test")
	public ModelAndView boardTest(
			@ModelAttribute("userInfo") EmpVO empVO
			) {
		ModelAndView mav = new ModelAndView();
		if (empVO == null) {
			throw new LoginException("sdfsdf");
		}
		mav.addObject("userInfo", empVO);
		mav.setViewName("blank");
		return mav;
	}
	
	
	@GetMapping("/list")
	public ModelAndView boardList(@ModelAttribute SearchHelper searchHelper, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("board/list");
		
		if (searchHelper.getPageNumber() < 0) searchHelper.setPageNumber(0);
		
		log.info(searchHelper.toString());
		
		HttpSession session = request.getSession();
		EmpVO empVO = (EmpVO) session.getAttribute("userInfo");
		
		if (empVO != null) {
			mav.addObject("userInfo", empVO);
		} else {
			mav.addObject("userInfo", new EmpVO());
		}
		
		int currentPage = searchHelper.getPageNumber();
		if (currentPage < 1) currentPage = 1;

		int pageSize = searchHelper.getPageSize();
		if (pageSize < 1) pageSize = 10;

		int offset = (currentPage - 1) * pageSize;
		searchHelper.setOffset(offset);
		
		List<BoardVO> list = boardService.selectList(searchHelper);

		int totalRecords = boardService.selectListCount(searchHelper);
		int totalPages = (int) Math.ceil((double) totalRecords / pageSize);

		mav.addObject("list", list);
		mav.addObject("totalRecords", totalRecords);
		mav.addObject("pageSize", pageSize);
		mav.addObject("totalPages", totalPages);
		mav.addObject("currentPage", currentPage);
		
		return mav;
	}
	
	@RequestMapping("/write")
	public ModelAndView boardWrite(@ModelAttribute SearchHelper searchHelper, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		log.info(searchHelper.toString());
		
		HttpSession session = request.getSession();
		EmpVO empVO = (EmpVO) session.getAttribute("userInfo");
		mav.addObject("userInfo", empVO);
		mav.addObject("searchHelper", searchHelper);
		mav.addObject("result", new BoardVO());
		mav.setViewName("board/write");
		return mav;
	}
	
	@PostMapping("/save")
	public ModelAndView boardSave(
			@ModelAttribute SearchHelper searchHelper, 
			@ModelAttribute BoardVO boardVO,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		log.info(searchHelper.toString());
		
		HttpSession session = request.getSession();
		EmpVO vo = (EmpVO) session.getAttribute("userInfo");
		boardVO.setBoardWriter(vo.getEName());
		
		if (boardVO.getBoardNo() == null) {
			boardService.insertBoard(boardVO);
		} else {
			boardService.updateBoard(boardVO);
		}
		
		String url = StringUtil.searchString("/board/view", searchHelper);
		mav.setViewName("redirect:" + url + "&boardNo=" + boardVO.getBoardNo());
		return mav;
	}
	
	@GetMapping("/view")
	public ModelAndView boardView(@ModelAttribute SearchHelper searchHelper, 
			@RequestParam("boardNo") Long boardNo,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		log.info(searchHelper.toString());
		
		HttpSession session = request.getSession();
		EmpVO vo = (EmpVO) session.getAttribute("userInfo");
		mav.addObject("userInfo", vo != null ? vo : new EmpVO());
		
		Optional<BoardVO> result = boardService.selectBoard(boardNo);
		
		StringBuilder str = new StringBuilder();
		str.append("/board/list?");
		str.append("pageNumber=" + searchHelper.getPageNumber());
		str.append("&pageSize=" + searchHelper.getPageSize());
		str.append("&searchType=" + searchHelper.getSearchType());
		str.append("&searchKeyword=" + searchHelper.getSearchKeyword());
		
		if (result.isPresent()) {
			mav.setViewName("board/view");
			mav.addObject("info", result.get());
			mav.addObject("link", str.toString());
		} else {
			mav.setViewName("redirect:" + str.toString());
		}
		
		return mav;
	}
	
	@GetMapping("/modify/{boardNo}")
	public ModelAndView boardModify(
			@PathVariable Long boardNo,
			HttpServletRequest request
		) {
		ModelAndView mav = new ModelAndView();
		
		Optional<BoardVO> result = boardService.selectBoard(boardNo);
		
		mav.addObject("result", result.get());
		
		HttpSession session = request.getSession();
		EmpVO empVO = (EmpVO) session.getAttribute("userInfo");
		mav.addObject("userInfo", empVO);
		
		mav.setViewName("board/write");
		return mav;
	}
	
	@GetMapping("/delete/{boardNo}")
	public ModelAndView boardDelete(@PathVariable Long boardNo) {
		ModelAndView mav = new ModelAndView();
		
		boardService.deleteBoard(boardNo);
		
		mav.setViewName("redirect:/board/list?pageNumber=1&pageSize=10");
		return mav;
	}
	
	
	
}
