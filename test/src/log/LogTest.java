package log;

import org.apache.log4j.Logger;


public class LogTest {
	
	
	public static Logger logger1 = Logger.getLogger(LogTest.class);
    public static void main(String[] args) {
    	
       //logger1
       //logger1.trace("����logger1��trace");
       logger1.debug("����logger1��debug");
       logger1.info("����logger1��info");
       logger1.warn("����logger1��warn");
       logger1.error("����logger1��error");
       logger1.fatal("����logger1��fatal");
    }
}
