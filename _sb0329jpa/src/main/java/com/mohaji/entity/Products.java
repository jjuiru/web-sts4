package com.mohaji.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // 테이블 creat 역할 스키마/데이터 파일을 사용하지 않는다. 
public class Products {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private Long prodId;  //카멜표현방식
	private String prodName;
	private Integer prodPrice;
}
