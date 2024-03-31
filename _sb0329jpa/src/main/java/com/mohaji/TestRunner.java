package com.mohaji;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// 웹 사이트가 구동하면 최초에 딱 한번 실행된다. 서블릿의 init 메소드와 동일한 기능을한다. 
@Component
@Slf4j // 로그 사용하려고 등록
public class TestRunner implements ApplicationRunner {
	
	@Autowired
	DataSource dataSource;	
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Connection connection=dataSource.getConnection();
		log.info("DBCP:"+dataSource.getClass());//사용하는 DBCP 타입 확인
		log.info("Url:"+connection.getMetaData().getURL());
		log.info("UserName:"+connection.getMetaData().getUserName());
		
		//  jdbctemplate
		jdbcTemplate.execute("INSERT INTO Products"+"(prod_name, prod_price) values ('버킷햇1',6900)");	
		jdbcTemplate.execute("INSERT INTO Products"+"(prod_name, prod_price) values ('버킷햇2',6900)");
		jdbcTemplate.execute("INSERT INTO Products"+"(prod_name, prod_price) values ('버킷햇3',6900)");
		jdbcTemplate.execute("INSERT INTO Member"+"(name, city, street, zipcode) values ('효닝','서울','정릉',6900)");
	}
}
