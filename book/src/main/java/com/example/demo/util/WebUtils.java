package com.example.demo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.example.demo.domain.Member;

public class WebUtils {

	public static HttpSession getSession(HttpServletRequest req) {
		return req.getSession();
	}
	
	public static Member getMember(HttpServletRequest req) {
		HttpSession hs = req.getSession();
		return (Member)hs.getAttribute("member");
	}

}
