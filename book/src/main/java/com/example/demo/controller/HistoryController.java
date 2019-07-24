package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.History;
import com.example.demo.domain.Member;
import com.example.demo.domain.PopularSearch;
import com.example.demo.exception.BizErrContants;
import com.example.demo.exception.BizException;
import com.example.demo.service.HistoryService;
import com.example.demo.util.WebUtils;

@Controller
public class HistoryController {
	
	@Autowired
	HistoryService historyService;

	@RequestMapping("/getMyHistory")
	@ResponseBody
	public Page<History> getMyHistory(HttpServletRequest req, HttpServletResponse res,
			@PageableDefault(size = 10, page = 0, sort = "regdt", direction = Direction.DESC) Pageable pageable) {
		Member member = WebUtils.getMember(req);
		Page<History> result = null;
		try {
			result = historyService.findByMember(member, pageable);
		} catch (Exception e) {
			throw new BizException(BizErrContants.E0000);
		}
		
		return result;
	}
	
	@RequestMapping("/getPopularSearch")
	@ResponseBody
	public Page<PopularSearch> getPopularSearch(HttpServletRequest req, HttpServletResponse res,
			@PageableDefault(size = 10, page = 0) Pageable pageable) {
		Page<PopularSearch> result = null;
		try {
			result = historyService.findPopularSearch(pageable);
		} catch (Exception e) {
			throw new BizException(BizErrContants.E0000);
		}
		
		return result;
	}

}
