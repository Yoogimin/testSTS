package com.icia.mbp.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.icia.mbp.dao.MemberDAO;
import com.icia.mbp.dto.MemberDTO;

@Service
public class MemberService {

	@Autowired
	private MemberDAO mdao;
	
	private ModelAndView mav;
	
	@Autowired
	private BCryptPasswordEncoder pwEnc;
	
	@Autowired
	private HttpServletRequest request;
	
	// 파일업로드 폴더
	String savePath = request.getServletContext().getRealPath("src/main/webapp/resources/profile/").replace(".metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\", "");
	
	
	
	public String idCheck(String mId) {
		String result = null;
		
		String checkId = mdao.idCheck(mId);
		
		if(checkId==null) {
			result = "OK";
		} else {
			result = checkId;
		}
		
		return result;
	}

	public ModelAndView mJoin(MemberDTO member) {
		mav = new ModelAndView();
		
		// (1) 비밀번호 암호화
		// member.getMPw() : 우리가 입력한 비밀번호  
		// pwEnc.encode(member.getMPw()) : 입력한 비밀번호 암호화
		// member.setMPw(pwEnc.encode(member.getMPw()))
		// : 암호화 된 비밀번호를 다시 member객체에 저장
		member.setMPw(pwEnc.encode(member.getMPw()));
		
		// db에서 똑같은 비밀번호가 같은지 다른지 확인..
		
		
		// (2) 주소 처리
		// (22223)인천 미추홀구 매소홀로488번길 6-32, 태승빌딩 4층
		String mAddr = "(" + member.getAddr1() + ")" + member.getAddr2() + ", " + member.getAddr3();
		member.setMAddr(mAddr);
		
		
		// (3) 파일 업로드 : profile 폴더
		
		MultipartFile mProfile = member.getMProfile();
		
		if(!mProfile.isEmpty()) {
			String uuid = UUID.randomUUID().toString().substring(0,8);
			String fileName = mProfile.getOriginalFilename();
			String mProfileName = uuid + "_" + fileName;
			member.setMProfileName(mProfileName);
			
			try {
				mProfile.transferTo(new File(savePath + mProfileName));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}			
		} else {
			member.setMProfileName("default.jpg");
		}
		
		////////////////////////////////////////////////////////////////////////////////
		
		try {	// 회원가입 성공
			mdao.mJoin(member);
			System.out.println("[4]dao → service : 회원가입 성공");
		} catch(Exception e) {	// 회원가입 실패
			System.out.println("[4]dao → service : 회원가입 실패");
		}
		
		
		
		
		
		
		
		return mav;
	}

}











