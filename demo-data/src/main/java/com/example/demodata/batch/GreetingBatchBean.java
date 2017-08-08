package com.example.demodata.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class GreetingBatchBean {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Scheduled(cron = "*/20 * * * * *")
	public void cronJob() {
		logger.info("Cron Job started...");
		logger.info("Cron Job ended...");
	}

	@Scheduled(initialDelay = 5000, fixedRate = 10000)
	public void fixedRateJobWithInitialDelay() {
		logger.info("Fixed rate Job started...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Fixed rate Job ended...");
	}
	
	@Scheduled(initialDelay = 5000, fixedDelay = 10000)
	public void fixedDelayJobWithInitialDelay() {
		logger.info("Fixed delay Job started...");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Fixed delay Job ended...");
	}
}
