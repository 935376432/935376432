package com.test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * @功能描述： commons-logging的测试类
 */
public class CommonsLoggingTest
{
    public static Log log = LogFactory.getLog(CommonsLoggingTest.class);


    @Test
    public void test()
    {
        log.debug("debug()...");
        log.info("info()...");
        log.error("error()...");
    }


}