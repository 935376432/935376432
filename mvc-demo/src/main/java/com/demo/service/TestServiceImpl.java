package com.demo.service;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public String sayHello() {
        return "hi ,spring 4 ";
    }
}