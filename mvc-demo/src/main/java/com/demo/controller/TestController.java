package com.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.service.TestService;

/**
 * test
 */

@RestController
@RequestMapping(path = "/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/aa", method = RequestMethod.GET)
    public ResponseEntity<?> test() {
        return ResponseEntity.ok(testService.sayHello());
    }

    @RequestMapping(value = "/bb",method = RequestMethod.GET)
    public ResponseEntity<?> getTest (HttpServletRequest request,Principal principal,Pageable pageable){
        return ResponseEntity.ok(testService.sayHello());
    }

    @RequestMapping(value = "/cc",method = RequestMethod.GET)
    public ResponseEntity<?> getList (Pageable pageable){

        return ResponseEntity.ok(testService.sayHello());
    }

}