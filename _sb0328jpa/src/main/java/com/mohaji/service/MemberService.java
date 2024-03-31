package com.mohaji.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mohaji.model.Product;
import com.mohaji.repository.MemberMapper;
import com.mohaji.repository.ProductMapper;

import java.util.List;

@Service 
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

}
