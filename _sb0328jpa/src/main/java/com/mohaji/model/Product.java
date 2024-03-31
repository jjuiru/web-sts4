package com.mohaji.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@RequiredArgsConstructor // 요구된 arg의 생성자를 (final 타입이 붙은) 자동생성해준다. 
@Setter
@Getter
@ToString
public class Product {

	private Long prodId; // 테이블에서는 _ 사용하고 java 에선 대문자로 구분 
	// properties 에서 mybatis.configuration.map-underscore-to-camel-case=true 부분이 이 매핑작업을 해준다.
	final private String prodName; // 기본값을 주면 바꿀 수 없음
	final private int prodPrice;
}
