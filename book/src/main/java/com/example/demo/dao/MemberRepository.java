package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	@Query("SELECT m FROM Member m WHERE m.id=?1")
	Member getMemberById(String id);

	@Query("SELECT m FROM Member m WHERE m.id=:#{#mem.id} AND m.pwd=:#{#mem.pwd}")
	Member loginConfirm(@Param("mem")Member member);
}
