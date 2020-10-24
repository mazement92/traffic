package pre.task.traffic.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pre.task.traffic.member.dto.MemberDto;
import pre.task.traffic.member.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController { 
	@Autowired 
	private MemberService memberService; 
		
	/**
	 * 회원 가입
	 * @param request HttpServletRequest
	 * @param memberDto MemberDto
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertMember", method=RequestMethod.POST) 
	public @ResponseBody Map<String, Object> insertMember(HttpSession session, @RequestBody MemberDto memberDto) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		if(session.getAttribute("token") != null) {
			throw new Exception("로그인 상태에선 회원 가입을 진행할 수 없습니다.");
		}
		
		if(memberDto.getMemberId().length() > 10 || memberDto.getMemberPwd().length() > 10) {
			throw new Exception("ID와 PW는 10자 이내로 입력해주세요.");
		}
		
		try {
			memberService.insertMember(memberDto);
			resultMap.put("msg", "회원가입이 완료되었습니다.");
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("회원가입에 실패하였습니다. " + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 로그인, 토큰 생성
	 * @param session HttpSession
	 * @param memberDto MemberDto
	 * @return Map<String, Object>
	 * @throws Exception 
	 */
	@RequestMapping(value = "/login" , method=RequestMethod.POST) 
	public @ResponseBody Map<String, Object> login(HttpSession session, @RequestBody MemberDto memberDto) throws Exception {
		if(session.getAttribute("token") != null) {
			throw new Exception("이미 로그인된 상태입니다.");
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			String token = memberService.getMemberInfo(memberDto);
			session.setAttribute("token", token);
			session.setMaxInactiveInterval(1800);
			resultMap.put("msg", "환영합니다");
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("로그인에 실패하였습니다. " + e.getMessage());
		}
		return resultMap;
	}
	
	/**
	 * 로그아웃, 토큰 제거
	 * @param session HttpSession
	 * @return Map<String, Object>
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout" , method=RequestMethod.POST) 
	public @ResponseBody Map<String, Object> logout(HttpSession session) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		try {
			session.removeAttribute("token");
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("로그아웃에 실패하였습니다. " + e.getMessage());
		}
		return resultMap;
	}
}
