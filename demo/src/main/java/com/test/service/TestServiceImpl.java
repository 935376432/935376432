/*
 *jiji java
 */
package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.model.TestInfo;
import com.test.reository.TestRepository;

@Service
public class TestServiceImpl implements TestService{


    @Autowired
    private TestRepository testRepository;

    @Override
    public TestInfo addTest(TestInfo testInfo) {
        // 保存到数据库
        //testRepository.save(testInfo);

        return testInfo;
    }

}
