package com.test._test1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test._test1.dao.MemberDao;
import com.test._test1.dto.Member;



@Service
public class GetMemberListPrintService {

	@Autowired
	private MemberDao memberDao;
	
	public List<Member> getMemberListPrintService() {
		return memberDao.selectAll();
	}
	
	public Member getSelectOneService(int id) {
		return memberDao.selectOne(id);
	}
	
	public Member getUpdateService(Member member) {
		return memberDao.update(member);
	}
	public Member getInputService(Member member) {
		return memberDao.insert(member);
	}
	
	public void delete(int id) {
		memberDao.delete(id);
	}
}
