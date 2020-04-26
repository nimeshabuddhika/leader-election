package com.nimesha.leader.election.leaderelection.config;

import com.nimesha.leader.election.leaderelection.task.TaskRunner;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Nimesha Buddhika on 4/26/2020 7:37 PM
 */
@Component
@Configuration
public class ZooKeeperConfig {
    private static final Logger logger = LoggerFactory.getLogger(ZooKeeperConfig.class);
    private static final String ZOO_HOST = "localhost:2181";

    @Order(0)
    @Bean
    public CuratorFramework leaderClient() {
        CuratorFramework client = CuratorFrameworkFactory
                .newClient(ZOO_HOST, new ExponentialBackoffRetry(1000, 3));
        client.start();
        return client;
    }

    @Order(1)
    @Bean
    public LeaderSelector leaderSelector(CuratorFramework leaderClient, TaskRunner taskRunner) {
        LeaderSelector leaderSelector = new LeaderSelector(leaderClient, "/mutex/select/leader/for/job/testJob",
                new LeaderSelectorListener() {
                    @Override
                    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                        logger.info("stateChanged");
                    }

                    @Override
                    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
                        logger.info("Select leader");
                        taskRunner.run();
                    }
                });
        return leaderSelector;
    }

}
