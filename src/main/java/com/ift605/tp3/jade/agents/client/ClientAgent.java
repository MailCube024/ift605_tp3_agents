package com.ift605.tp3.jade.agents.client;

import com.ift605.tp3.jade.agents.client.behaviours.ClientTestBehaviour;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2015-11-14.
 */
public class ClientAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(ClientAgent.class);

    @Override
    public void setup() {
        logger.info("Client agent started");

        final String basicAgentName = (String) getArguments()[0];
        final String constantAgentName = (String) getArguments()[1];
        addBehaviour(new ClientTestBehaviour(basicAgentName, constantAgentName));
    }

    @Override
    public void takeDown() {
    }
}
