package com.example.demo.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "history")
public class History {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String searchWord;

	@Column(nullable = false)
	private Timestamp regdt;

	@ManyToOne(optional = false)
	@JoinColumn(name = "member_id")
	private Member member;

	public History() {
	}
	
	public History(String searchWord, Timestamp regdt, Member member) {
		this.searchWord = searchWord;
		this.regdt = regdt;
		this.member = member;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getsearchWord() {
		return searchWord;
	}

	public void setsearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public Timestamp getregdt() {
		return regdt;
	}

	public void setregdt(Timestamp regdt) {
		this.regdt = regdt;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

}
