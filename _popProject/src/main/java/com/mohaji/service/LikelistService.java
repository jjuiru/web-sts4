package com.mohaji.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohaji.model.Likelist;
import com.mohaji.repository.LikelistMapper;
import com.mohaji.repository.MemberMapper;


import java.util.List;

@Service 
public class LikelistService {

    @Autowired
    private LikelistMapper likelistMapper; 
    
    public String insertDeleteLike(String status, String userId,String popCode) {
    	if(status.equals("off")) {
    		likelistMapper.deleteLikelist(userId, popCode);
    		return "off";
    	}else if(status.equals("on")){
    		likelistMapper.insertLikelist(userId, popCode);
    		return "on";
    	} System.out.println("값이 없어요");
		return popCode;
    }
    
    public String checkLike (String userId, String popCode){
    	List<Likelist> list= likelistMapper.selectAllLikelist();
    	for(Likelist value: list) {
    		if(userId.equals(value.getUserId())&&popCode.equals(value.getPopCode())){
    			return "on";
    		}
    	}
    	return "off";
    }
  
}
