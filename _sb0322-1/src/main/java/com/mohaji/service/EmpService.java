package com.mohaji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohaji.dao.EmpDao;
import com.mohaji.dto.Emp;

@Service
public class EmpService {

	@Autowired
	EmpDao empDao;
	
	public List<Emp> empSelectDeptno(int deptno) {
		return empDao.empSelectDeptno(deptno);
	}
	
	public Emp empSelectOneService(int empno) {
		return empDao.empSelectOneEmpno(empno);
	}
//	
//	public Member getUpdateService(Member member) {
//		return memberDao.update(member);
//	}
//	public Member getInputService(Member member) {
//		return memberDao.insert(member);
//	}
//	
//	public void delete(int id) {
//		memberDao.delete(id);
//	}
	
}
