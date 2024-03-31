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
    
//    public boolean likeCheck(String userId,String popCode) {
//        boolean check = likelistMapper.existsByUserIdAndPopCode(userId, popCode);
//        return check;
//    }
    
    public void insertLike (String check, String userId, String popCode){
    	System.out.println(check);
    	if(check.equals("on")) {
    		likelistMapper.insertLikelist(userId, popCode);
    	}else if(check.equals("off")) {
    		likelistMapper.deleteLikelist(userId, popCode);
    	}else {
    		System.out.println("온오프 값이 없어요");
    	}
    }
   
}
