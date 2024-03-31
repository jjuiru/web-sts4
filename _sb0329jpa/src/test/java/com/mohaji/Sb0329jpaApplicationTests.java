package com.mohaji;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mohaji.model.Member;
import com.mohaji.repository.MemberMapper;
import com.mohaji.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class Sb0329jpaApplicationTests {

	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	MemberService memberService;
	
//	@Autowired
//	ProductMapper productMapper;
//	
//	@Autowired
//	private ProductService productService;
	
	@Test
	void contextLoadsMember() {
		System.out.println(memberMapper.count());
		log.info("member 레코드 수 :{}",memberMapper.count());
	}
	
	@Test
	@DisplayName("테이블리스트")
	void selectAllMember() {
		System.out.println(memberMapper.selectAllMember());
		log.info("member 레코드 리스트 :{}",memberMapper.selectAllMember());
	}
	
	@Test
	void selctMemberById() {
		System.out.println(memberMapper.selectMemberById(1L));
		log.info("member 레코드 1 :{}",memberMapper.selectMemberById(1L));
	}
	
	@Test
	@DisplayName("member 테이블 자료 입력")
	void insertMember() {
		Member member = Member.builder().name("김하나")
				.city("인천").street("부산")
				.zipcode(567001).build();
		memberMapper.insertMember(member);
	}
	

//	@Test
//	void contextLoads() {
//		System.out.println(productMapper.count());
//		log.debug("products 레코드 수 :{}",productMapper.count());
//		log.info("products 레코드 수 :{}",productMapper.count());
//	}
//	
//	@Test
//	public void getProductById() {
//		Product product= productService.getProductById(1L); //long 타입
//		log.debug("product: {}",product);
//		log.info("product: {}",product);
//	}
//	
//	@Test
//	public void getAllProducts() {
//		List<Product>products = productService.getAllProducts(); 
//		log.debug("product: {}",products);
//		log.info("product: {}",products);
//	}
//	
//	@Test
//	public void addProduct() {
//		productService.addProduct(new Product("쿤달 샴푸", 7900)); 
//		productService.addProduct(new Product("마스크팩", 1000)); 
//		productService.addProduct(new Product("티셔트", 5900)); 
//	}	
}
