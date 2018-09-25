/*
 *jiji java
 */
package com.test.web.api;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.TestInfo;
import com.test.reository.UserRepository;
import com.test.service.TestService;

@RestController
@RequestMapping(path = "/test")
public class TestController {



    /** test业务逻辑接口 */
    @Autowired
    private TestService testService;

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(path = "/addTest",method = RequestMethod.POST)
    public ResponseEntity<?> addTest (HttpServletRequest request,Principal principal,@RequestBody TestInfo testInfo){


        TestInfo info = testService.addTest(testInfo);
        Map<String,Object> map = new  HashMap<>();
        map.put("id", 1);

        return ResponseEntity.ok(map);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getTest (HttpServletRequest request,Principal principal){




        return ResponseEntity.ok(userRepository.findAll());
    }


}
