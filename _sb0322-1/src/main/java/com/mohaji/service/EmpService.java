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
	
	public void empUpdate(Emp emp) {
		empDao.update(emp);
	}
	public void empInput(Emp emp) {
		empDao.input(emp);
	}
	
	public void delete(Emp emp) {
		empDao.delete(emp);
	}
	
	
}
