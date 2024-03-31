package com.mohaji;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mohaji.dto.BoardDto;
import com.mohaji.mapper.BoardMapper;

@SpringBootTest
class Sb0325MyBatisApplicationTests {
	
	@Autowired
	BoardMapper boardMapper;

	@Test
	void contextLoads() {
	}
	
	@Test
	@DisplayName("member 테이이블의 개수")
	void memberCount() {
		System.out.println(boardMapper.selectCount());
	}

	@Test
	@DisplayName("member 테이블 내용")
	void memberSelectAll() {
		for (BoardDto dto: boardMapper.selectBoardList() ) {
			System.out.println(dto);
		}
	}
	
//	@Test
//	@DisplayName("member 테이블 입력")
//	void memberInsert() {
//		BoardDto dto=BoardDto.builder().email("aaa@aaa.aaa").name("이쭈이").meno("깜찍").age(23).build();
//		boardMapper.insertBoard(dto);
//			
//		}
	
//	@Test
//	@DisplayName("member 테이블 삭제")
//	void memberDelete() {
//		int befor=boardMapper.selectCount();
//		boardMapper.deleteBoard(31);
//		int after=boardMapper.selectCount();
//		assertEquals(befor-1, after);		
//		}
	
	@Test
	@DisplayName("member 테이블 업데이트")
	void memberUpdate() {
		BoardDto dto=BoardDto.builder().email("bbb@bbb.bbb").name("이쭈이").meno("깜찍").age(23).id(29).build();
		boardMapper.updateBoard(dto);
			
		}
	}

