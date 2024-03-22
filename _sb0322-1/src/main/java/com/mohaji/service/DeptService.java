package com.mohaji.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohaji.dao.DeptDao;
import com.mohaji.dto.Dept;


@Service
public class DeptService {

	@Autowired
	private DeptDao dept;
	
	public List<Dept> deptSelectAll() {
		return dept.deptSelectAll();
	}
	
//	public Dept getSelectOneService(int id) {
//		return dept.selectOne(id);
//	}
//	
//	public Member getUpdateService(Member member) {
//		return memberDao.update(member);
//	}
//	public Member getInputService(Member member) {
//		return memberDao.insert(member);
//	}
//	
//	public void delete(int id) {
//		memberDao.delete(id);
//	}
}