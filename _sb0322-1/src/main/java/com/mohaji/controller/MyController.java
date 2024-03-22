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

@Controller
public class MyController {
	
	@Autowired
	State state;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String root(Model model) { // dao 만들징 않고 빠르게 하기
		List<Dept> list = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
		while (rs.next()) {
			Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
					.loc(rs.getString("loc")).build();
			list.add(dept);
		}
		model.addAttribute("list", list);

		return "root";
	}
	
	@GetMapping("/selectEmp")
	public String selectEmp(Model model, int deptno ) {
		state.setDeptno(deptno);
		List<Dept> list = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
		while (rs.next()) {
			Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
					.loc(rs.getString("loc")).build();
			list.add(dept);
		}
				
		List<Emp> list2 = new ArrayList<>();
		SqlRowSet rs2 = jdbcTemplate.queryForRowSet("select * from emp where deptno = ?", deptno);
		while (rs2.next()) {
			Emp emp = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename"))
					.job(rs2.getString("job")).mgr(rs2.getInt("mgr"))
					.hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
					.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
			list2.add(emp);
		}
		model.addAttribute("list2", list2);
		model.addAttribute("list", list);
		return "root";
		
	}
	@GetMapping("/updateForm1")
	public String updateForm1(Model model, String empno) {
		List<Dept> list = new ArrayList<>();
		SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
		while (rs.next()) {
			Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
					.loc(rs.getString("loc")).build();
			list.add(dept);
		}
				
		List<Emp> list2 = new ArrayList<>();
		SqlRowSet rs2 = jdbcTemplate.queryForRowSet("select * from emp where deptno = ?", state.getDeptno());
		while (rs2.next()) {
			Emp emp = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename"))
					.job(rs2.getString("job")).mgr(rs2.getInt("mgr"))
					.hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
					.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
			list2.add(emp);
		}
		model.addAttribute("list2", list2);
		model.addAttribute("list", list);
		
		Emp emp1 = null;
		SqlRowSet rs3 = jdbcTemplate.queryForRowSet("select * from emp where empno = ?", empno);
		while (rs3.next()) {
			emp1 = Emp.builder().empno(rs3.getInt("empno")).ename(rs3.getString("ename"))
					.job(rs3.getString("job")).mgr(rs3.getInt("mgr"))
					.hiredate(rs3.getString("hiredate")).sal(rs3.getInt("sal"))
					.comm(rs3.getInt("comm")).deptno(rs3.getInt("deptno")).build();

			System.out.println(emp1);
	}
	model.addAttribute("list3", emp1);
		return "root";
	}
	
	
	@PostMapping("/update")
	public String update(Model model, Emp emp) {
    state.setEmpno(emp.getEmpno());
    jdbcTemplate.update("update emp set ename=?, job=?, mgr=?, sal=?, comm=?  where empno=?",
    emp.getEname(), emp.getJob(), emp.getMgr(), emp.getSal(), emp.getComm(), emp.getEmpno());
    

    List<Dept> list = new ArrayList<>();
	SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
	while (rs.next()) {
		Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
				.loc(rs.getString("loc")).build();
		list.add(dept);
	}
			
	List<Emp> list2 = new ArrayList<>();
	SqlRowSet rs2 = jdbcTemplate.queryForRowSet("select * from emp where deptno = ?", state.getDeptno());
	while (rs2.next()) {
		Emp emp3 = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename"))
				.job(rs2.getString("job")).mgr(rs2.getInt("mgr"))
				.hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
				.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
		list2.add(emp3);
	}
	model.addAttribute("list2", list2);
	model.addAttribute("list", list);
	
	Emp emp1 = null;
	SqlRowSet rs3 = jdbcTemplate.queryForRowSet("select * from emp where empno = ?", emp.getEmpno());
	while (rs3.next()) {
		emp1 = Emp.builder().empno(rs3.getInt("empno")).ename(rs3.getString("ename"))
				.job(rs3.getString("job")).mgr(rs3.getInt("mgr"))
				.hiredate(rs3.getString("hiredate")).sal(rs3.getInt("sal"))
				.comm(rs3.getInt("comm")).deptno(rs3.getInt("deptno")).build();

		System.out.println(emp1);
}
model.addAttribute("list3", emp1);
       return "root";
}


	@PostMapping("/input")
	public String input(Model model, Emp emp) {
	    jdbcTemplate.update("INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?)",
	        emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getSal(), emp.getComm(), emp.getDeptno());	
		
		Emp emp1=null;
		 List<Dept> list = new ArrayList<>();
			SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
			while (rs.next()) {
				Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
						.loc(rs.getString("loc")).build();
				list.add(dept);
			}
					
			List<Emp> list2 = new ArrayList<>();
			SqlRowSet rs2 = jdbcTemplate.queryForRowSet("select * from emp where deptno = ?", state.getDeptno());
			while (rs2.next()) {
				Emp emp3 = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename"))
						.job(rs2.getString("job")).mgr(rs2.getInt("mgr"))
						.hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
						.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
				list2.add(emp3);
			}
			model.addAttribute("list2", list2);
			model.addAttribute("list", list);
			
			SqlRowSet rs3 = jdbcTemplate.queryForRowSet("select * from emp where empno = ?", emp.getEmpno());
			while (rs3.next()) {
				emp1 = Emp.builder().empno(rs3.getInt("empno")).ename(rs3.getString("ename"))
						.job(rs3.getString("job")).mgr(rs3.getInt("mgr"))
						.hiredate(rs3.getString("hiredate")).sal(rs3.getInt("sal"))
						.comm(rs3.getInt("comm")).deptno(rs3.getInt("deptno")).build();

				System.out.println(emp1);
		}
		model.addAttribute("list3", emp1);
		
		return "root";
	}
		@PostMapping("/delete")
		public String delete(Model model, Emp emp) {
		    jdbcTemplate.update("DELETE FROM emp WHERE empno = " + emp.getEmpno());  
		    
			Emp emp1=null;
			 List<Dept> list = new ArrayList<>();
				SqlRowSet rs = jdbcTemplate.queryForRowSet("select * from dept");
				while (rs.next()) {
					Dept dept = Dept.builder().deptno(rs.getInt("deptno")).dname(rs.getString("dname"))
							.loc(rs.getString("loc")).build();
					list.add(dept);
				}
						
				List<Emp> list2 = new ArrayList<>();
				SqlRowSet rs2 = jdbcTemplate.queryForRowSet("select * from emp where deptno = ?", state.getDeptno());
				while (rs2.next()) {
					Emp emp3 = Emp.builder().empno(rs2.getInt("empno")).ename(rs2.getString("ename"))
							.job(rs2.getString("job")).mgr(rs2.getInt("mgr"))
							.hiredate(rs2.getString("hiredate")).sal(rs2.getInt("sal"))
							.comm(rs2.getInt("comm")).deptno(rs2.getInt("deptno")).build();
					list2.add(emp3);
				}
				model.addAttribute("list2", list2);
				model.addAttribute("list", list);
				
				SqlRowSet rs3 = jdbcTemplate.queryForRowSet("select * from emp where empno = ?", emp.getEmpno());
				while (rs3.next()) {
					emp1 = Emp.builder().empno(rs3.getInt("empno")).ename(rs3.getString("ename"))
							.job(rs3.getString("job")).mgr(rs3.getInt("mgr"))
							.hiredate(rs3.getString("hiredate")).sal(rs3.getInt("sal"))
							.comm(rs3.getInt("comm")).deptno(rs3.getInt("deptno")).build();

					System.out.println(emp1);
			}
			model.addAttribute("list3", emp1);
			
			return "root";
		    		    
		}
}
