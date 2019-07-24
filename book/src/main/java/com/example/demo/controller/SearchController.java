package com.example.demo.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.History;
import com.example.demo.domain.Member;
import com.example.demo.exception.BizErrContants;
import com.example.demo.exception.BizException;
import com.example.demo.service.HistoryService;
import com.example.demo.service.SearchService;
import com.example.demo.util.WebUtils;

@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService;
	
	@Autowired
	HistoryService historyService;
	
	@RequestMapping("/bookList")
	public String bookList(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (null == WebUtils.getMember(req)) {
			return "redirect:/";
		}
		return "booksearch";
	}

	@RequestMapping("/bookSearch")
	@ResponseBody
	public Map<String, Object> bookSearch(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(value="searchWord")String searchWord ,@RequestParam(value="page", defaultValue = "1")int page, @RequestParam(value="isSearch")boolean isSearch) throws Exception {
		Member member = WebUtils.getMember(req);
		try {
			if (isSearch) {
				historyService.save(new History(searchWord, Timestamp.valueOf(LocalDateTime.now()), member));
			}
		} catch(Exception e) {
			throw new BizException(BizErrContants.E0000);
		}
		return searchService.searchBooks(searchWord, page, false);
	}
	
	@RequestMapping("/detail")
	@ResponseBody
	public Map<String, Object> detail(@RequestParam(value="isbn")String isbn) {
		Map<String, Object> result = null;
		try {
			result = searchService.getDetail(isbn);
		} catch (Exception e) {
			throw new BizException(BizErrContants.E0000);
		}
		
		return result;
	}
	

}
