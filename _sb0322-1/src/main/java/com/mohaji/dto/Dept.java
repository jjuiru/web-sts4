package com.mohaji.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class Dept {
	private int deptno;
	private String dname;
	private String loc;
	
}
