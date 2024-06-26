package com.mohaji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.mohaji.service.GetMemberListPrintService;

@Controller
public class AjaxController {
	
	@Autowired
	private GetMemberListPrintService memberService;

	@GetMapping("/ajax1")
	public String ajax1() {
		return "ajax1";
	}
	
	@GetMapping("/list2")
		public String list2(Model model) {
	    model.addAttribute("list2",memberService.getMemberListPrintService());
			return "list2";
		}
	}

