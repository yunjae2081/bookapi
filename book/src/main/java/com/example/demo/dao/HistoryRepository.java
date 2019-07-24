package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.domain.History;
import com.example.demo.domain.Member;
import com.example.demo.domain.PopularSearch;

public interface HistoryRepository extends JpaRepository<History, String>{

	@Query("SELECT h FROM History h WHERE h.member=?1")
	Page<History> findByMember(Member member, Pageable pageable);

	@Query("SELECT new com.example.demo.domain.PopularSearch(h.searchWord, COUNT(h)) FROM History h GROUP BY h.searchWord ORDER BY COUNT(h) desc")
	Page<PopularSearch> findPopularSearch(Pageable pageable);

}
