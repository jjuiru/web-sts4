package com.mohaji.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mohaji.dto.Emp;


@Mapper
public interface EmpMapper {// 인터페이스타입 (mybatis 에서 타입은 자동으로 생서어해준다)
	List<Emp> selectEmpList();
	
	int selectCount();

    void insertEmp(Emp emp);
//
//	void updateHitCount(int boardIdx);
//	
	void updateEmp(Emp emp);
	
	void deleteEmp(int empIdx);
}
