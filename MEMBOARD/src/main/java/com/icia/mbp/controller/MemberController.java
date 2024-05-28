package com.icia.mbp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.mbp.dto.MemberDTO;
import com.icia.mbp.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService msvc;
	
	private ModelAndView mav = new ModelAndView();
	

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index() {
		return "index";
	}
	
	// joinForm : 회원가입 페이지로 이동
	@RequestMapping(value="/joinForm", method=RequestMethod.GET)
	public String joinForm() {
		return "member/join";
	}
	
	// idCheck : 아이디 중복
	// ResponseBody 어노테이션 : return값이 jsp가 아닌 data로 반환된다.
	@RequestMapping(value="/idCheck", method=RequestMethod.POST)
	public @ResponseBody String idCheck(@RequestParam("mId") String mId) {
		String result = msvc.idCheck(mId);
		return result;
	}
	
	// mJoin : 회원가입
	@RequestMapping(value="/mJoin", method=RequestMethod.POST)
	public ModelAndView mJoin(@ModelAttribute MemberDTO member) {
		System.out.println("[1] " + member);
		mav = msvc.mJoin(member);
		
		return mav;
	}
	
	
	
}





