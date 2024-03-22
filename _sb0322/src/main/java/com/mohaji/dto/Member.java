package com.mohaji.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@Builder
public class Member {

	private int id;
	private String name;
	private int age;
	private String email;
	private LocalDateTime regdate;
	private String meno;

	public Member(String name, int age, String email, LocalDateTime regdate, String meno) {
		this.name = name;
		this.age = age;
		this.email = email;
		this.regdate = regdate;
		this.meno = meno;
	}
	
	public Member() {
	}
	
	public Member(String string, String string2) { // test용 생성자
		// TODO Auto-generated constructor stub
	};
}
