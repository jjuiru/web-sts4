package com.mohaji.controller;

import java.util.List;

import javax.xml.stream.events.Comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohaji.service.CommentService;

@Controller
public class CommentController {

	private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comments")
    public String listComments(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = 10; // 페이지당 아이템 수
        int offset = page * pageSize; // 시작 오프셋 계산
        List<Comment> comments = commentService.getComments(offset, pageSize);
        model.addAttribute("comments", comments);
        return "comment/list";
    }
}
