package com.mohaji.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mohaji.dto.BoardDto;


@Mapper
public interface BoardMapper {// 인터페이스타입 (mybatis 에서 타입은 자동으로 생서어해준다)
	List<BoardDto> selectBoardList();
	
	int selectCount();

    void insertBoard(BoardDto boardDto);
//
//	void updateHitCount(int boardIdx);
//	
	void updateBoard(BoardDto board);
	
	void deleteBoard(int boardIdx);
}
