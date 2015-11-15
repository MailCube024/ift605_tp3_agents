package com.ift605.tp3.jade.agents.basic;

import com.ift605.tp3.jade.agents.basic.behaviors.DerivateBasicEquationBehaviour;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2015-11-14.
 */
public class BasicEquationAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(BasicEquationAgent.class);

    @Override
    public void setup() {
        final String otherAgentName = (String) this.getArguments()[0];
        addBehaviour(new DerivateBasicEquationBehaviour(otherAgentName));
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
