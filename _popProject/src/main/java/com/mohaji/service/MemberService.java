package com.mohaji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohaji.model.Member;
import com.mohaji.repository.MemberMapper;


@Service
public class MemberService {
	
	@Autowired
	MemberMapper memberMapper;
	
	public boolean userIdCheck(String userId) {
		List<Member>list=memberMapper.selectAllMember();
		for(Member member:list) {
			if(member.getUserId().equals(userId))
				return false;
		}
		return true;		
	}	
	public boolean memberSignUp(Member member) {
		if(userIdCheck(member.getUserId())==false)
		return false;
		memberMapper.insertMember(member);
		return true;
	}
}
