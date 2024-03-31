package com.mohaji;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mohaji.repository.MemberMapper;
import com.mohaji.spring.Member;

@SpringBootTest
class Sb0326MyBatisVer21ApplicationTests {

	@Autowired
	MemberMapper meberMapper;

	@Test
	@DisplayName("Member 건수")
	void contextLoads() {
		System.out.println(meberMapper.selectCount());
	}
	
	@Test
	@DisplayName("Member email 리스트")
	void selectEmail() {
		System.out.println(meberMapper.selectByEmail("aaa@aaa.aaa"));
	}
	
	@Test
	@DisplayName("Member email 리스트")
	void insert() {
		Member dto=Member.builder().email("aaa@aaa.aaa").name("이쭈이").meno("깜찍").age(23).build();
		System.out.println(meberMapper.insert(dto));
	}
	
	@Test
	@DisplayName("Member email 리스트")
	void delete() {
		System.out.println(meberMapper.delete(34));
	}
	
	@Test
	@DisplayName("Member email 리스트")
	void update() {
		Member dto=Member.builder().email("aaa@aaa.aaa").name("이쭈이").meno("깜찍").age(23).id(29).build();
		System.out.println(meberMapper.update(dto));
	}

}
