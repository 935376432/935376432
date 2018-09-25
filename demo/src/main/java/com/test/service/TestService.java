/*
 *jiji java
 */
package com.test.service;

import com.test.model.TestInfo;

public interface TestService {


    /**
     * 增加test
     * @param testInfo test信息
     * @return test结果
     */
    TestInfo addTest(TestInfo testInfo);

}
