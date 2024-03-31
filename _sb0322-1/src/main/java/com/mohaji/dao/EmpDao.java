package com.mohaji.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mohaji.dto.Emp;

@Repository
public class EmpDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Emp> empSelectDeptno(int deptno) {
		List<Emp> results = jdbcTemplate.query(
				"select * from emp where deptno = ?", //객체를 오버라이딩으로 재정의 해서 생산성을 높인다. 
				new RowMapper<Emp>() { // 
					@Override
					public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
						Emp emp = new Emp(
								rs.getInt("empno"),
								rs.getString("ename"),
								rs.getString("job"),
								rs.getInt("mgr"),
								rs.getString("hiredate"),
								rs.getInt("sal"),
								rs.getInt("comm"),
								rs.getInt("deptno"));
						return emp;
					}
				},deptno );
		return results;
	}	
	
	public Emp empSelectOneEmpno(int empno) {
		Emp results = jdbcTemplate.queryForObject(
				"select * from emp where empno = ?", //객체를 오버라이딩으로 재정의 해서 생산성을 높인다. 
				new RowMapper<Emp>() { // 
					@Override
					public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
						Emp emp = new Emp(
								rs.getInt("empno"),
								rs.getString("ename"),
								rs.getString("job"),
								rs.getInt("mgr"),
								rs.getString("hiredate"),
								rs.getInt("sal"),
								rs.getInt("comm"),
								rs.getInt("deptno"));
						return emp;
					}
				},empno );
		return results;
	}	
	
	public List<Emp> selectAll() {
		List<Emp> results = jdbcTemplate.query(
				"select * from emp", //객체를 오버라이딩으로 재정의 해서 생산성을 높인다. 
				new RowMapper<Emp>() { // 
					@Override
					public Emp mapRow(ResultSet rs, int rowNum) throws SQLException {
						Emp emp = new Emp(
								rs.getInt("empno"),
								rs.getString("ename"),
								rs.getString("job"),
								rs.getInt("mgr"),
								rs.getString("hiredate"),
								rs.getInt("sal"),
								rs.getInt("comm"),
								rs.getInt("deptno"));
						return emp;
					}
				});
		return results;
	}
	
	
	
	public void update(Emp emp) {
		jdbcTemplate.update("update emp set ename=?, job=?, mgr=?, sal=?, comm=?  where empno=?",
			    emp.getEname(), emp.getJob(), emp.getMgr(), emp.getSal(), emp.getComm(), emp.getEmpno());
	}
	
	public void input(Emp emp) {
		 jdbcTemplate.update("INSERT INTO emp(empno, ename, job, mgr, hiredate, sal, comm, deptno) VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP, ?, ?, ?)",
			        emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getSal(), emp.getComm(), emp.getDeptno());
	}
	
	public void delete(Emp emp) {
		 jdbcTemplate.update("DELETE FROM emp WHERE empno = " + emp.getEmpno());
	}
	
	
	
}
