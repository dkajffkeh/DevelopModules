package com.spring.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component("batch")
public class MainBatch {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MainBatch.class);

	
	public void callBatchA() {
		
		LOGGER.info("=========================================");
		LOGGER.info("================ A콜배치 실행 ===============");	
		LOGGER.info("=========================================");
		
		System.out.println("실행!");
		
	}
	

}
