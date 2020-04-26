package com.nimesha.leader.election.leaderelection.schedulers.impl;

import com.nimesha.leader.election.leaderelection.schedulers.TestScheduler;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
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
public class TestSchedulerImpl implements TestScheduler {

    private static final Logger logger = LoggerFactory.getLogger(TestSchedulerImpl.class);

    private final LeaderSelector leaderSelector;
    private final Environment environment;

    @Autowired
    public TestSchedulerImpl(LeaderSelector leaderSelector, Environment environment) {
        this.leaderSelector = leaderSelector;
        this.environment = environment;
    }

    @Scheduled(cron = "0/10 * * * * *")
    @Override
    public void run() {
        logger.info("Scheduler executed : {}", environment.getProperty("local.server.port") );
        leaderSelector.start();
    }
}
