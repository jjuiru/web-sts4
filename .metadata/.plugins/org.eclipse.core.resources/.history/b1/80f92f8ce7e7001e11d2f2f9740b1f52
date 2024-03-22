package com.test._test1.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import com.test._test1.dto.Member;

@Repository
public class MemberDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Member> selectAll() {
		List<Member> results = jdbcTemplate.query(
				"select * from MEMBER", //객체를 오버라이딩으로 재정의 해서 생산성을 높인다. 
				new RowMapper<Member>() { // 
					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						Member member = new Member(
								rs.getString("name"),
							    rs.getInt("age"),
							    rs.getString("email"),
							    rs.getTimestamp("regdate").toLocalDateTime(),
							    rs.getString("meno"));
						member.setId(rs.getInt("ID"));
						return member;
					}
				});
		return results;
	}
	
	
	public Member selectOne(int id) {
	    String sql = "SELECT * FROM MEMBER WHERE id = ?";
	    Member result = jdbcTemplate.queryForObject(
	        sql,
	        new RowMapper<Member>() {
	            @Override
	            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
	                Member member = new Member(
	                		rs.getString("name"),
						    rs.getInt("age"),
						    rs.getString("email"),
						    rs.getTimestamp("regdate").toLocalDateTime(),
						    rs.getString("meno"));
	                member.setId(rs.getInt("ID"));
	                return member;
	            }
	        }, id);
	    return result;
	}
	//-----------------------------------------------update query------------------------------------------
	public Member insert(Member member) {
	jdbcTemplate.update("INSERT INTO member(name, age, email, regdate, meno)VALUES(?, ?, ?, now(),?)",
			member.getName(),member.getAge(),member.getEmail(),member.getMeno());
	return member;
}
	
	public Member update(Member member) {
		System.out.println(member);
	    jdbcTemplate.update("update member set name=?, age=?, email=?, meno=?, regdate=now() where id=?",
	        member.getName(), member.getAge(), member.getEmail(), member.getMeno(), member.getId());
	    return member;
	}
	
	public void delete(int id) {
	    jdbcTemplate.update("DELETE FROM member WHERE id = " + id);   
	}
	
	public int count() {//테스트용 count 처음 db 접속시 간단하게 테스트 할 수 있따.
		return jdbcTemplate.queryForObject("select count(*) from MEMBER", Integer.class);
	}
}
