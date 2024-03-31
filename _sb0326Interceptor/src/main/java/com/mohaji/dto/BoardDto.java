package com.mohaji.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDto { //member테이블을 사용함
	private int id;
	private String email;
	private String meno;
	private String name;
	private int age;
	private String regdate;
	
}
