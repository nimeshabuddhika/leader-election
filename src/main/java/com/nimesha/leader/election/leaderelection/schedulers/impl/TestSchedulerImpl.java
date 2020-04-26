package com.nimesha.leader.election.leaderelection.schedulers.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Nimesha Buddhika on 4/26/2020 7:27 PM
 */
@Component
public class TestSchedulerImpl {

    private static final Logger logger = LoggerFactory.getLogger(TestSchedulerImpl.class);


    private final Environment environment;

    @Autowired
    public TestSchedulerImpl(Environment environment) {
        this.environment = environment;
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void run() {
        logger.info("Executed : {}", environment.getProperty("local.server.port"));
    }
}
