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
		popboardMapper.insertPopboard(popboard);
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
	
		
}
