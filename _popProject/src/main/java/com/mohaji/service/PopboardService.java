package com.mohaji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohaji.model.Popboard;
import com.mohaji.repository.PopboardMapper;

@Service
public class PopboardService {

	@Autowired
	PopboardMapper popboardMapper;

	public List<Popboard> selectAllPopboard(){	
		return popboardMapper.selectAllPopboard();
	}
	
	public void insertPopboard(Popboard popboard) {
		System.out.println(popboard);
		popboardMapper.insertPopboard(popboard);
	}
		
	public List<Popboard> selectIdPopboard(String userId){	
		return popboardMapper.selectIdPopboard(userId);
	}
	
	
	public void deletePopboard(Long num) {
		popboardMapper.deletePopboard(num);
	}
	
	public void updatePopboard(Popboard popboard) {
		popboardMapper.updatePopboard(popboard);
	}
	
	public List<Popboard> selectPopboard(String popCode) {
		return popboardMapper.selectPopboard(popCode);
	}
	
	public Double starsValue(String popCode) {
		Double star = 0.0;
		int count = 0;
		List<Popboard> list = popboardMapper.selectPopboard(popCode);
		for (Popboard board : list) {
		    star += (double) board.getStar(); // 누적 합산을 위해 +=
		    count++; // count를 증가시킴
		}
		double val = (count != 0) ? star / (double) count : 0.0; // count가 0이 아닐 때만 계산하여 val에 할당
		System.out.println(val);
		return val;
	}		
}
