package com.test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;


/**
 * @���������� commons-logging�Ĳ�����
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