package com.mohaji.service;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohaji.repository.CommentMapper;

@Service
public class CommentService {
	private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<Comment> getComments(int offset, int limit) {
        return commentMapper.getComments(offset, limit);
    }
	
}
