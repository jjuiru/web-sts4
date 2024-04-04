package com.mohaji.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.mohaji.model.Member;
import com.mohaji.service.ArtboardService;
import com.mohaji.service.LikelistService;
import com.mohaji.service.LoginCheckService;
import com.mohaji.service.MemberService;
import javax.servlet.http.HttpServletRequest;



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
	
	HttpSession session;
	
	private void setLoginCAttribute(Model model, HttpServletRequest request) {
        session = request.getSession(false);
        boolean loginC = (session != null && session.getAttribute("userId") != null);
        model.addAttribute("loginC", loginC);
        if (loginC) {
            String userId = (String) session.getAttribute("userId");
            model.addAttribute("userId", userId);
        }
    }

	@GetMapping("/")
	public String main(Model model, HttpServletRequest request) {
		setLoginCAttribute(model, request);
		model.addAttribute("artlist", artboardService.artboardList());		
		return "main";
	}
	
	@GetMapping("/main")
	public String main1(Model model, HttpServletRequest request) {
		setLoginCAttribute(model, request);
		model.addAttribute("artlist", artboardService.artboardList());	
		return "main";
	}
	
	@GetMapping("/view")
	public String view(Model model, String popCode, HttpServletRequest request) {
		setLoginCAttribute(model, request);
		model.addAttribute("pop",artboardService.artboardSelectCode(popCode));
		String userId=(String) session.getAttribute("userId");
		if(userId==null) {
			model.addAttribute("onOff", "off");
			return "artboard/view";
		}
		model.addAttribute("onOff", likelistService.checkLike(userId, popCode));
		return "artboard/view";
	}
	
	@GetMapping("/like")
	public String like(Model model, String status, String popCode, HttpServletRequest request) {
		setLoginCAttribute(model, request);
		String userId=(String) session.getAttribute("userId");
		model.addAttribute("onOff", likelistService.insertDeleteLike(status, userId, popCode) );
		model.addAttribute("pop",artboardService.artboardSelectCode(popCode));
		return "artboard/view";
	}	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login/loginPage";
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request,Model model,String userId, String pass) {
		boolean login=loginCheckService.loginCheck(userId, pass);
		System.out.println(userId);
		if(login==false) {
			model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
			return "login/loginPage";
		}
	    HttpSession session = request.getSession();
	    session.setAttribute("userId", userId);
	    setLoginCAttribute(model, request);
	    model.addAttribute("artlist", artboardService.artboardList());
			return "main";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request,Model model,String userId) {
		HttpSession session = request.getSession(false);
		if (session != null) {
		    session.removeAttribute("userId");
		}
		setLoginCAttribute(model, request);
		model.addAttribute("artlist", artboardService.artboardList());	

			return "main";
	}
	
	@GetMapping("/signUpPage")
	public String signUpPage(HttpServletRequest request,Model model) {
		setLoginCAttribute(model, request);
			return "login/signUpPage";
	}
	
	@GetMapping("/userIdCheck")
	public String userIdCheck(HttpServletRequest request,Model model, Member member) {
        boolean check=memberService.userIdCheck(member.getUserId()); // true 면 사용가능 false 면 사용불가
        System.out.println(check);
        System.out.println(member.getUserId());
		if(check==true) {
			model.addAttribute("userId", member.getUserId());
			model.addAttribute("pass", member.getPass());
			model.addAttribute("uname", member.getPass());
			model.addAttribute("email", member.getEmail());
			model.addAttribute("phoneNum", member.getPhoneNum());
			model.addAttribute("birth", member.getBirth());
			model.addAttribute("check", "사용가능한 아이디 입니다.");
			return "login/signUpPage";
		}else {
			model.addAttribute("error", "사용할 수 없는 아이디 입니다.");	
			model.addAttribute("pass", member.getPass());
			model.addAttribute("email", member.getEmail());
			model.addAttribute("uname", member.getPass());
			model.addAttribute("phoneNum", member.getPhoneNum());
			model.addAttribute("birth", member.getBirth());
			return "login/signUpPage";
		}
	}
	
	@PostMapping("/signUp")
	public String signUp(HttpServletRequest request,Model model, Member member) {
		System.out.println(member);
		if(!memberService.memberSignUp(member)) {
			model.addAttribute("error", "사용할 수 없는 아이디 입니다.");	
			model.addAttribute("pass", member.getPass());
			model.addAttribute("email", member.getEmail());
			model.addAttribute("uname", member.getPass());
			model.addAttribute("phoneNum", member.getPhoneNum());
			model.addAttribute("birth", member.getBirth());
			model.addAttribute("artlist", artboardService.artboardList());
			return "main";
		}
		memberService.memberSignUp(member);
		setLoginCAttribute(model, request);
		model.addAttribute("signUp", "ok");
		model.addAttribute("artlist", artboardService.artboardList());	
			return "main";
	}
	
	@GetMapping("/artlist")
	public String artList(HttpServletRequest request,Model model) {
		System.out.println(artboardService.artboardList());
		setLoginCAttribute(model, request);
		model.addAttribute("artlist", artboardService.artboardList());	
		return "artboard/list";
	}
	
	@GetMapping("/myPage")
	public String myPage(HttpServletRequest request,Model model) {
		setLoginCAttribute(model, request);
		String userId=(String) session.getAttribute("userId");
		System.out.println("세션에 저장된값"+userId);
		model.addAttribute("user", memberService.memberOneSelect(userId));
		model.addAttribute("myList", likelistService.MylikeList(userId));
	
		return "login/myPage";		
	}

	
	@PostMapping("/userUpdate")
	public String myPage(HttpServletRequest request,Model model, Member member) {
		System.out.println("업데이트 대상:"+member);
		memberService.memberUpdat(member);
		return "redirect:/myPage";
	}
	@GetMapping("/date")
	public String date(HttpServletRequest request,Model model, Member member) {
		setLoginCAttribute(model, request);
		return "login/date";
	}

}
