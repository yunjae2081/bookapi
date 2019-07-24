package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.domain.Member;
import com.example.demo.exception.BizErrContants;
import com.example.demo.exception.BizException;
import com.example.demo.service.MemberService;

@Controller
@EnableAutoConfiguration
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@RequestMapping("/")
	public String join(HttpServletRequest req, HttpServletResponse res) throws Exception {
		return "main";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse res, @ModelAttribute Member member, RedirectAttributes ra) throws Exception {
		if (!memberService.login(req, member)) {
			ra.addFlashAttribute("resultMsg", "아이디/패스워드를 확인해주세요");
			return "redirect:/";
		}
		return "redirect:/bookList";
	}
	
	@RequestMapping("/pwdCheck")
	@ResponseBody
	public Map<String, Object> test(@RequestParam(value = "id") String id,
			   @RequestParam(value="pw") String pw,
			   @RequestParam(value="pwchk") String pwchk) throws Exception{
		Map<String, Object> result = null;
		try {
			
			result = memberService.join(new Member(id, pw, Timestamp.valueOf(LocalDateTime.now())), pwchk);
		} catch (Exception e) {
			throw new BizException(BizErrContants.E0000);
		}
		return result;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest req, HttpServletResponse res) throws Exception {
		memberService.logout(req);
		return "redirect:/";
	}

}


