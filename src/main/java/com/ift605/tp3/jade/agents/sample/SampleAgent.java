package com.ift605.tp3.jade.agents.sample;

import com.ift605.tp3.jade.agents.sample.behaviors.IncrementBaseNumber;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2015-11-08.
 */
public class SampleAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(SampleAgent.class);

    @Override
    public void setup() {
        final String otherAgentName = (String) this.getArguments()[0];
        addBehaviour(new IncrementBaseNumber(this, otherAgentName));
    }

    @Override
    public void takeDown() {
    }
}
