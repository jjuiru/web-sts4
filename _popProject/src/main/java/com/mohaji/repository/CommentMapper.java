package com.mohaji.repository;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

@Mapper
public interface CommentMapper {
	 @Select("SELECT * FROM comments LIMIT #{offset}, #{limit}")
	    List<Comment> getComments(@Param("offset") int offset, @Param("limit") int limit);
	 
	  
	}

