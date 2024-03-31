package com.mohaji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mohaji.mapper.BoardMapper;


@Controller
public class MyController {
	
	@Autowired
	private BoardMapper boardMapper;

	
	@GetMapping("/")
	public String root(Model model) {
		model.addAttribute("cnt",boardMapper.selectCount());
		return "root";
	}
	
	@GetMapping("/root")
	public String root2(Model model) {
		model.addAttribute("cnt",boardMapper.selectCount());
		return "root";
	}
	
}
