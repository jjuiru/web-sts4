package com.mohaji.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;//dbcp와 관련되어 있다. 
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@PropertySource("classpath:/application.properties") //resources 로부터 바로 읽어온다. 
public class DatabaseConfiguration {
	
	@Autowired
	private ApplicationContext applicationContext; //java bean을 돌리기위한 인터페이스
	
	@Bean
	@ConfigurationProperties(prefix="spring.datasource.hikari") //application.properties에서 읽어오는 값
	public HikariConfig hikariConfig() {
		System.out.println("hikariConfig()"); // 실행되는지 확인
		return new HikariConfig();
	}
	
	@Bean
	@ConfigurationProperties(prefix="mybatis.configuration") // db 접속과 관련되어 읽어온다.
	public org.apache.ibatis.session.Configuration mybatisConfig(){
		System.out.println("mybatisConfig()");
		return new org.apache.ibatis.session.Configuration(); 
	}
	
	@Bean
	public DataSource dataSource() {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println("dataSource()");
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources
				("classpath:/mapper/**/sql-*.xml")); // 리소스 아래 있는 ** 모든 폴더내의 sql-*.xml 형식의 여러 파일을 매핑한다는 뜻
		sqlSessionFactoryBean.setConfiguration(mybatisConfig());
		System.out.println("sqlSessionFactory()");
		return sqlSessionFactoryBean.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
		System.out.println("sqlSessionTemplate()");
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}
