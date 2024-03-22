package com.mohaji.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.mohaji.dto.Dept;


@Repository
public class DeptDao {

	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	public List<Dept> deptSelectAll() {
		List<Dept> results = jdbcTemplate.query(
				"select * from dept", //객체를 오버라이딩으로 재정의 해서 생산성을 높인다. 
				new RowMapper<Dept>() { // 
					@Override
					public Dept mapRow(ResultSet rs, int rowNum) throws SQLException {
						Dept dept = new Dept(
								rs.getInt("deptno"),
								rs.getString("dname"),
								rs.getString("loc"));
						return dept;
					}
				});
		return results;
	}	
}
