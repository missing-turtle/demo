package com.example.demo.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class SearchHelper {
	
	// 현재 페이지 번호
	private int pageNumber;
	// 한번에 가져올 데이터 행의 갯수
	private int pageSize;
	// 데이터 행의 몇번째부터 가져 올건지 설정하는 변수 offset
	private int offset;
	// 검색 키워드
	private String searchKeyword;
	// 검색 종류
	private String searchType;
	// 게시판 PK
	private Long boardNo;

}
