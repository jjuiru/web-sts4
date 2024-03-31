package com.mohaji.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.mohaji.service.ArtboardService;
import com.mohaji.service.LikelistService;
import com.mohaji.service.LoginCheckService;
import com.mohaji.service.MemberService;



@Controller
public class MainController {
	
	@Autowired
	ArtboardService artboardService;
	
	@Autowired
	LikelistService likelistService;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	LoginCheckService loginCheckService;

	@GetMapping("/")
	public String main(Model model) {
		model.addAttribute("artlist", artboardService.artboardList());		
		return "main";
	}
	
	@GetMapping("/main")
	public String main1(Model model) {
		model.addAttribute("artlist", artboardService.artboardList());	
		return "main";
	}
	
	@GetMapping("/view")
	public String view(Model model, String popCode) {
		System.out.println(popCode);
		model.addAttribute("pop",artboardService.artboardSelectCode(popCode));
		return "artboard/view";
	}
	
	@GetMapping("/like")
	public String like(Model model, String status, String popCode) {
		String userId = "admin";
		likelistService.insertLike(status, userId, popCode);
		model.addAttribute("pop",artboardService.artboardSelectCode(popCode));
		return "artboard/view";
	}	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login/loginPage";
	}
	@PostMapping("/login")
	public String login(Model model,String userId, String pass) {
			
			return "main";
	}
}
