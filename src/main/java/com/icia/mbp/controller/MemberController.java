package com.icia.mbp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.icia.mbp.dto.MemberDTO;
import com.icia.mbp.dto.SearchDTO;
import com.icia.mbp.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService msvc;
	
	@Autowired
	private HttpSession session;
	
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
	
	// loginForm : 로그인 페이지로 이동
	@RequestMapping(value="/loginForm", method=RequestMethod.GET)
	public String loginForm() {
		return "member/login";
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
		mav = msvc.mJoin(member);
		return mav;
	}
	
	// mLogin : 로그인
	@RequestMapping(value="/mLogin", method=RequestMethod.POST)
	public ModelAndView mLogin(@ModelAttribute MemberDTO member) {
		mav = msvc.mLogin(member);
		return mav;
	}
	
	// mList : 회원목록
	@RequestMapping(value="/mList", method=RequestMethod.GET)
	public ModelAndView mList(@RequestParam(value="page", required = false, defaultValue = "1") int page,
							  @RequestParam(value="limit", required = false, defaultValue = "5") int limit) {
		mav = msvc.mList(page, limit);
		return mav;
	}
	
	// mSearch : 회원검색
	@RequestMapping(value="/mSearch", method=RequestMethod.GET)
	public ModelAndView mSearch(@ModelAttribute SearchDTO search) {
		mav = msvc.mSearch(search);
		return mav;
	}
	
	// mView : 회원상세보기
	@RequestMapping(value="/mView", method=RequestMethod.GET)
	public ModelAndView mView(@RequestParam("mId")String mId) {
		mav = msvc.mView(mId);
		return mav;
	}
	
	// mModiForm : 회원정보 수정페이지
	@RequestMapping(value="/mModiForm", method=RequestMethod.GET)
	public ModelAndView mModiForm(@RequestParam("mId")String mId) {
		mav = msvc.mModiForm(mId);
		return mav;
	}
	
	// mModify : 회원정보 수정
	@RequestMapping(value="/mModify", method=RequestMethod.POST)
	public ModelAndView mModify(@ModelAttribute MemberDTO member) {
		mav = msvc.mModify(member);
		return mav;
	}
	
	// mDelete : 회원정보 삭제
	@RequestMapping(value="/mDelete", method=RequestMethod.GET)
	public ModelAndView mDelete(@RequestParam("mId")String mId,
								@RequestParam("mProfileName")String mProfileName) {
		mav = msvc.mDelete(mId, mProfileName);
		session.invalidate();
		return mav;
	}
	
	// mLogout : 로그아웃
	@RequestMapping(value="/mLogout", method=RequestMethod.GET)
	public String mLogout() {
		session.invalidate();
		return "index";
	}
	
	
	
	
	
}





