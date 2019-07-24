package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberRepository;
import com.example.demo.domain.Member;
import com.example.demo.util.PasswordEncoder;
import com.example.demo.util.WebUtils;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	@Transactional
	public void save(Member member) {
		memberRepository.save(member);
	}

	@Transactional
	public Member getMemberById(String id) {
		return memberRepository.getMemberById(id);
	}

	@Transactional
	public Map<String, Object> join(Member member, String pwchk) {

		Member mem = getMemberById(member.getId());
		String msg = "가입이 완료되었습니다.";
		if (null == mem) {
			if (member.getPwd().equals(pwchk)) {
				member.setPwd(PasswordEncoder.encode(member.getPwd()));
				memberRepository.save(member);
			} else {
				msg = "패스워드를 확인해주세요";
			}
		} else {
			msg = "이미 사용중인 ID 입니다.";
		}

		Map<String, Object> result = new HashMap<>();
		result.put("msg", msg);
		return result;
	}

	@Transactional
	public boolean login(HttpServletRequest req, Member member) {
		member.setPwd(PasswordEncoder.encode(member.getPwd()));
		Member mem = memberRepository.loginConfirm(member);
		if (null != mem) {
			HttpSession hs = WebUtils.getSession(req);
			hs.setAttribute("member", member);
		} else {
			return false;
		}
		return true;
	}
	
	@Transactional
	public void logout(HttpServletRequest req) {
		HttpSession hs = WebUtils.getSession(req);
		hs.removeAttribute("member");
	}
}
