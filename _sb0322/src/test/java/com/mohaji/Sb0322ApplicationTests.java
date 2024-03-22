package com.mohaji;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.mohaji.dao.MemberDao;
import com.mohaji.dto.Member;

@SpringBootTest
class Sb0322ApplicationTests {

	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Test
	@DisplayName("Jdbc 테스트")
	void jdbcTeset() {
	 SqlRowSet rs=jdbcTemplate.queryForRowSet("select * from member where id=?", 29);
	 rs.next();
		assertEquals(29,rs.getInt("id"));
	}
	
	@Test
	void contextLoads() {		
		// 여기에서 Dao 테스트 코드를 작성할 수 있다. 
	}
	
	//@Test
	@DisplayName("assertEquals로 테스트")
	public void test3() {
		int before=memberDao.count();
		Member member = Member.builder().email("leegh936").name("가나다").build();
		memberDao.insert(member);
		int after=memberDao.count();
		assertEquals(before,after);
	}
	
	@Test
	@DisplayName("selectAll() 테스트")
	public void test1() {
		System.out.println(memberDao.selectAll());
	}
	
	//@Test
	@DisplayName("insert() 테스트")
	public void test2() {
		Member member2 = new Member("name", "leegh936"); 
		//String 타입의 2개가 있는 생성자를 만들어야 한다.
		Member member = Member.builder().email("leegh936").name("가나다").build();
		// 자동으로 나머지 값을 null로 넣어줘서 따로 생성자를 갯수대로 만들지 않아도 된다.
		System.out.println(memberDao.insert(member));
	}
}
