package com.example.demo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.HistoryRepository;
import com.example.demo.domain.History;
import com.example.demo.domain.Member;
import com.example.demo.domain.PopularSearch;

@Service
public class HistoryService {
	
	@Autowired
	HistoryRepository historyRepository;

	@Transactional
	public void save(History history) {
		historyRepository.save(history);
	}

	@Transactional
	public Page<History> findByMember(Member member, Pageable pageable) {
		return historyRepository.findByMember(member, pageable);
	}

	@Transactional
	public Page<PopularSearch> findPopularSearch(Pageable pageable) {
		return historyRepository.findPopularSearch(pageable);
	}

}
