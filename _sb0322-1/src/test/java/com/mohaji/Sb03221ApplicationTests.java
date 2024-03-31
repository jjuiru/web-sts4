package com.mohaji;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mohaji.dto.Emp;
import com.mohaji.mapper.EmpMapper;

@SpringBootTest
class Sb03221ApplicationTests {
	
	@Autowired
	EmpMapper empMapper;

	@Test
	void contextLoads() {
		System.err.println(empMapper.selectCount());
		
	}
	
	@Test
	@DisplayName("member 테이블 내용")
	void empSelectAll() {
		for (Emp dto: empMapper.selectEmpList() ) {
			System.out.println(dto);
		}
	}
	 	
	@Test
	@DisplayName("member 테이블 입력")
	void memberInsert() {
		Emp dto=Emp.builder().empno(1236).ename("하하")
				.job("STUDENT").mgr(2323).sal(300).comm(500).deptno(10).build();
		empMapper.insertEmp(dto);
			
		}
	
	@Test
	@DisplayName("member 테이블 삭제")
	void empDelete() {
		int befor=empMapper.selectCount();
		empMapper.deleteEmp(1234);
		int after=empMapper.selectCount();		
		}
	
	@Test
	@DisplayName("member 테이블 업데이트")
	void memberUpdate() {
		Emp dto=Emp.builder().ename("SUJUNG").job("SUJUNG").mgr(3333).sal(400).comm(999).empno(1111).build();
		empMapper.updateEmp(dto);
			
		}
	

}
