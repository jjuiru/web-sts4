package com.test._test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.test._test1.dao.MemberDao;
import com.test._test1.dto.Member;
import com.test._test1.service.GetMemberListPrintService;

@Controller
public class MyController {

//@Autowired
//private MemberDao memberDao;
	@Autowired
	private GetMemberListPrintService getMemberListPrintService;

//@Autowired
//private LoginCheckService loginCheckService;	
	@GetMapping("/")
	public String main(Model model) { // 1.dto를 직접 매개변수로 바로 받아 사용하는 방법
		model.addAttribute("memberAllList", getMemberListPrintService.getMemberListPrintService());
		return "list";
	}

	@GetMapping("/move")
	public String move() {
		return "list";
	}

	@GetMapping("/list") // 이벤트 발생시 요청
	public String list(Model model) { // 1.dto를 직접 매개변수로 바로 받아 사용하는 방법
		model.addAttribute("memberAllList", getMemberListPrintService.getMemberListPrintService());
		return "list";
	}

	@PostMapping("/input")
	public String input(Model model, Member member) {
		System.out.println(member);
		System.out.println("아무거나");
		model.addAttribute("member", getMemberListPrintService.getInputService(member));
		model.addAttribute("memberAllList", getMemberListPrintService.getMemberListPrintService());
		return "list";
	}

	@GetMapping("/updateForm")
	public String updat(Model model, int id) {
		System.out.println("id=" + id);
		model.addAttribute("member", getMemberListPrintService.getSelectOneService(id));
		model.addAttribute("memberAllList", getMemberListPrintService.getMemberListPrintService());
		return "updateForm";
	}
	
	@PostMapping("/update")
	public String update(Model model, Member member) {
		System.out.println(member.getId());
		getMemberListPrintService.getUpdateService(member);
		model.addAttribute("memberAllList", getMemberListPrintService.getMemberListPrintService());
		return "list";
	}
	@PostMapping("/delete")
	public String delete(Model model, int id) {
		System.out.println("id=" + id);
		getMemberListPrintService.delete(id);
		model.addAttribute("memberAllList", getMemberListPrintService.getMemberListPrintService());
		return "list";
	}
}
