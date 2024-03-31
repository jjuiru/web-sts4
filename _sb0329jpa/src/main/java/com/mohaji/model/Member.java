package com.mohaji.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Member {

	private Long memberId;
	@NonNull private String name;
	@NonNull private String city;
	@NonNull private String street;
	@NonNull private int zipcode;
	
}
