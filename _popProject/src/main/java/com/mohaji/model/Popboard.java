package com.mohaji.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Popboard {

		private Long num;
	    private String userId;
		private String popCode;
		private String title;
		private String content;
		private int star;
		private String regtime;	

}
