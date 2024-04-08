package com.mohaji.controller;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	//----------세션을 사용해 로그인 여부와 userId 값을 리턴(모든 로그인 체크에 사용가능) -------------
	HttpSession session;
	private void setLoginCAttribute(Model model, HttpServletRequest request) {
        session = request.getSession(false); //세션이 있으면 해당세션 반환, 존재하지 않을 때는 새로운 세션을 생성하지 않고 null을 반환
        boolean loginC = (session != null && session.getAttribute("userId") != null);
        model.addAttribute("loginC", loginC); //로그인 체크 여부 저장 true/false
        if (loginC) { // 로그인 상태일 때는 userId에 userId 값을 저장한다.
            String userId = (String) session.getAttribute("userId");
            model.addAttribute("userId", userId);
        }
    }

	//-----------첫 페이지---------------------
	@GetMapping("/")
	public String main(Model model, HttpServletRequest request) {
		setLoginCAttribute(model, request); //loginC,userId 반환
		model.addAttribute("artlist", artboardService.artboardList());		
		return "main";
	}
	
	//----------메인으로 이동시------------------
	@GetMapping("/main")
	public String main1(Model model, HttpServletRequest request) {
		setLoginCAttribute(model, request);//loginC,userId 반환
		model.addAttribute("artlist", artboardService.artboardList());	
		return "main";
	}
	
	//----------행사 클릭시 뜨는 뷰 페이지 행사정보--------------
	@GetMapping("/view")
	public String view(Model model, String popCode, HttpServletRequest request) {
		setLoginCAttribute(model, request);//loginC,userId 반환
		model.addAttribute("pop",artboardService.artboardSelectCode(popCode));
		model.addAttribute("count", likelistService.countPopCode(popCode));
		String userId=(String) session.getAttribute("userId");
		if(userId==null) {
			model.addAttribute("onOff", "off");
			model.addAttribute("count", likelistService.countPopCode(popCode));
			return "artboard/view";
		}
		model.addAttribute("onOff", likelistService.checkLike(userId, popCode));
		model.addAttribute("count", likelistService.countPopCode(popCode));
		return "artboard/view";
	}
	
	// ---------뷰 페이지에서 하트(좋아요)를 눌렀을 경우 추가 코드------------
	@GetMapping("/like")
	public String like(Model model, String status, String popCode, HttpServletRequest request) {
		setLoginCAttribute(model, request); //loginC,userId 반환
		model.addAttribute("pop",artboardService.artboardSelectCode(popCode)); //선택한 팝업 띄우기
		String userId=(String) session.getAttribute("userId"); //세션에서 유저 아이디 반환
		if(userId==null) { //아이디가 없을 경우(로그인이 아닐 경우
		model.addAttribute("text", "on");	
		model.addAttribute("count", likelistService.countPopCode(popCode));
		return "artboard/view";
		}
		//로그인 상태일 경우 저장여부 리턴
		model.addAttribute("onOff", likelistService.insertDeleteLike(status, userId, popCode));	
		model.addAttribute("count", likelistService.countPopCode(popCode));
		return "artboard/view";
	}	
	
	//----------로그인 페이지로 이동--------------
	@GetMapping("/loginPage")
	public String loginPage() {
		return "login/loginPage";
	}
	
	//-----------로그인시 컨트롤-------------------
	@PostMapping("/login")
	public String login(HttpServletRequest request,Model model,String userId, String pass) {
		boolean login=loginCheckService.loginCheck(userId, pass); //로그인 가능한지 체크
		if(login==false) { //아이디중복,비번다를경우
			model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
			return "login/loginPage";
		}
		//로그인 성공했을 경우 세션에 아이디 저장후 로그인 확인여부 리턴
		HttpSession session = request.getSession();
	    session.setAttribute("userId", userId);
	    setLoginCAttribute(model, request);
	    model.addAttribute("artlist", artboardService.artboardList());
			return "main";
	}
	
	//----------로그아웃시 컨트롤-------------------
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
	
	//--------회원가입 페이지로 이동------------------
	@GetMapping("/signUpPage")
	public String signUpPage(HttpServletRequest request,Model model) {
		setLoginCAttribute(model, request);
			return "login/signUpPage";
	}
	
	//----------아이디 중복확인-----------------------
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
	//---------회원가입 처리-----------------------
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
	
	//--------검색시 나오는 행사 리스트-----------------
	@GetMapping("/artlist")
	public String artList(HttpServletRequest request,Model model) {
		System.out.println(artboardService.artboardList());
		setLoginCAttribute(model, request);
		model.addAttribute("artlist", artboardService.artboardList());	
		return "artboard/list";
	}
	//----------마이페이지 이동-----------------------
	@GetMapping("/myPage")
	public String myPage(HttpServletRequest request,Model model) {
		setLoginCAttribute(model, request);
		String userId=(String) session.getAttribute("userId");
		System.out.println("세션에 저장된값"+userId);
		model.addAttribute("user", memberService.memberOneSelect(userId));
		model.addAttribute("myList", likelistService.mylikeList(userId));
	
		return "login/myPage";		
	}

	//----------나의 정보 수정-----------------------
	@PostMapping("/userUpdate")
	public String myPage(HttpServletRequest request,Model model, Member member) {
		System.out.println("업데이트 대상:"+member);
		memberService.memberUpdat(member);
		return "redirect:/myPage";
	}
	
	@GetMapping("/likeDelete")
	public String likeDelete(HttpServletRequest request, RedirectAttributes redirectAttributes,Model model, String popCode, String userId) {
		likelistService.mylikeListDelete(userId, popCode);
		model.addAttribute("popDel", "on");
		redirectAttributes.addFlashAttribute("popDel", "on");
		return "redirect:/myPage";		
	}
	
	
	//----------행사 일정표 확인----------------------
	@GetMapping("/date")
	public String date(HttpServletRequest request,Model model, Member member) {
		setLoginCAttribute(model, request); //loginC,userId 반환
		return "login/date";
	}
}
