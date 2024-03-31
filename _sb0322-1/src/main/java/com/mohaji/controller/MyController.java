package com.mohaji.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mohaji.dto.Dept;
import com.mohaji.dto.Emp;
import com.mohaji.dto.State;
import com.mohaji.service.DeptService;
import com.mohaji.service.EmpService;

@Controller
public class MyController {

	@Autowired
	DeptService deptservice;

	@Autowired
	EmpService empservice;

	@Autowired
	State state;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String root(Model model) { // dao 만들징 않고 빠르게 하기
		model.addAttribute("list", deptservice.deptSelectAll());
		return "root";
	}

	@GetMapping("/selectEmp")
	public String selectEmp(Model model, int deptno) {
		state.setDeptno(deptno);
		model.addAttribute("list", deptservice.deptSelectAll());
		model.addAttribute("list2", empservice.empSelectDeptno(state.getDeptno()));
		return "root";

	}

	@GetMapping("/updateForm1")
	public String updateForm1(Model model, String empno) {
		model.addAttribute("list", deptservice.deptSelectAll());
		model.addAttribute("list2", empservice.empSelectDeptno(state.getDeptno()));
		model.addAttribute("list3", empservice.empSelectOneService(Integer.parseInt(empno)));
		return "root";
	}

	@PostMapping("/update")
	public String update(Model model, Emp emp) {

		state.setEmpno(emp.getEmpno());		
		empservice.empUpdate(emp);

		model.addAttribute("list", deptservice.deptSelectAll());
		model.addAttribute("list2", empservice.empSelectDeptno(state.getDeptno()));
		model.addAttribute("list3", empservice.empSelectOneService(state.getEmpno()));
		return "root";
	}

	@PostMapping("/input")
	public String input(Model model, Emp emp) {
		empservice.empInput(emp);
		state.setEmpno(emp.getEmpno());

		model.addAttribute("list", deptservice.deptSelectAll());
		model.addAttribute("list2", empservice.empSelectDeptno(state.getDeptno()));
		model.addAttribute("list3", empservice.empSelectOneService(state.getEmpno()));
		return "root";
	}

	@PostMapping("/delete")
	public String delete(Model model, Emp emp) {
		empservice.delete(emp);
		model.addAttribute("list", deptservice.deptSelectAll());
		model.addAttribute("list2", empservice.empSelectDeptno(state.getDeptno()));
		return "root";
	}
}
