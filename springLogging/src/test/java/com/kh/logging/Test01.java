package com.kh.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test01 {

	@org.junit.Test
	public void test() {
		Logger logger = LoggerFactory.getLogger(Test01.class);
		
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
	}
}
