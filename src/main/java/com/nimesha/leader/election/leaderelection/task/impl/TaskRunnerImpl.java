package com.nimesha.leader.election.leaderelection.task.impl;

import com.nimesha.leader.election.leaderelection.task.TaskRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author Nimesha Buddhika on 4/26/2020 7:34 PM
 */
@Component
public class TaskRunnerImpl implements TaskRunner {
    private static final Logger logger = LoggerFactory.getLogger(TaskRunnerImpl.class);

    private final Environment environment;

    @Autowired
    public TaskRunnerImpl(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void run() {
        logger.info("Executed : {}", environment.getProperty("local.server.port"));
    }
}
