package com.ift605.tp3.jade.tools;

import jade.core.Agent;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2015-11-08.
 */
public class ContainerKiller {
    private static final Logger logger = LoggerFactory.getLogger(ContainerKiller.class);

    public static void killContainerOf(Agent agent) {
        final AgentContainer containerController = agent.getContainerController();
        agent.doDelete();
        new Thread(() -> {
            try {
                containerController.kill();
            } catch (StaleProxyException e) {
                // We're going to eat this.
                //logger.error("Had exception on killContainerOf", e);
            }
        }).start();
    }
}
