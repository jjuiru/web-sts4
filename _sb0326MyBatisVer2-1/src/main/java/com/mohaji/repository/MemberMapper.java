package com.mohaji.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.mohaji.spring.Member;

@Mapper
public interface MemberMapper {
	int selectCount();
	
	@Select("select count(*) from member")
	int count();
	
	@Select("select * from member where email=#{email}")
	List<Member> selectByEmail(String email);
	
	@Select("INSERT INTO MEMBER ( EMAIL,MENO,AGE,NAME,REGDATE) VALUES (#{email},#{meno}, #{age},#{name},now())")
	Member insert(Member member);

	@Select("DELETE from MEMBER where id = #{id}")
	Member delete(int id);
	
	@Select("UPDATE MEMBER SET EMAIL=#{email},NAME=#{name},AGE= #{age},MENO= #{meno} WHERE ID= #{id}")
	Member update(Member member);
	
}
