package com.example.demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.exception.BizErrContants;
import com.example.demo.exception.BizException;
import com.example.demo.util.ApiUtils;
import com.example.demo.util.PageUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class SearchService {
	
	private static final String KKO_API_KEY = "403c5f9e61f45aa64f4e7e39a951dcca";
	private static final String KKO_API_URL = "https://dapi.kakao.com/v3/search/book";

	@SuppressWarnings("unchecked")
	public Map<String, Object> searchBooks(String searchWord, int page, boolean isDetail) {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "KakaoAK " + KKO_API_KEY);
		
		Map<String, String> params = new HashMap<>();
		params.put("query", searchWord);
		if (isDetail) {
			params.put("target", "isbn");
		}
		
		page = page <= 0 ? 1 : page;
		params.put("page", String.valueOf(page));
		
		Map<String, Object> result = new HashMap<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			result = mapper.readValue(ApiUtils.callAPItoStr(KKO_API_URL, headers, params), new TypeReference<Map<String, Object>>() {
			});
			
			Map<String, Integer> meta = (Map<String, Integer>)result.get("meta");
			if (0 < meta.get("pageable_count")) {
				PageUtil pageUtil = new PageUtil(meta.get("pageable_count"), page);
				result.put("pageUtil", pageUtil);
			}
		} catch (Exception e) {
			throw new BizException(BizErrContants.E0000);
		}
		return result;
	}
	
	public Map<String, Object> getDetail(String isbn) {
		return this.searchBooks(isbn, 1, true);
	}
	
}
