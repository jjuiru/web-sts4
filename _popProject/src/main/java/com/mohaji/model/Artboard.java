package com.mohaji.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@AllArgsConstructor
@ToString
public class Artboard {

	private String popCode;
	private String popName;
	private String startDay;
	private String endDay;
	private String content;
	private String rink;
	private String img;
	private String place;
}
