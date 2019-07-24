package com.example.demo.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "member")
public class Member {

	@Id
	private String id;

	@JsonIgnore
	@Column(nullable = false)
	private String pwd;

	@Column(nullable = false)
	private Timestamp regdt;

	public Member() {
		
	}

	public Member(String id, String pwd, Timestamp regdt) {
		this.id = id;
		this.pwd = pwd;
		this.regdt = regdt;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public Timestamp getregdt() {
		return regdt;
	}

	public void setregdt(Timestamp regdt) {
		this.regdt = regdt;
	}
}
