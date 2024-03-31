package com.mohaji.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@ToString
public class Product {

	private Long prodId; // 테이블에서는 _ 사용하고 java 에선 대문자로 구분 
	// properties 에서 mybatis.configuration.map-underscore-to-camel-case=true 부분이 이 매핑작업을 해준다.
	private String prodName; // 기본값을 주면 바꿀 수 없음
	private int prodPrice;
	public Product(Long prodId, String prodName, int prodPrice) {
		super();
		this.prodId = prodId;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	public Product(String prodName, int prodPrice) {
		super();
		this.prodName = prodName;
		this.prodPrice = prodPrice;
	}
	
      public Product() {  }
}
