package com.ift605.tp3.jade.agents.constant;

import com.ift605.tp3.jade.agents.constant.behaviors.DerivateConstantEquationBehaviour;
import jade.core.Agent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2015-11-14.
 */
public class ConstantAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(ConstantAgent.class);

    @Override
    public void setup() {
        final String otherAgentName = (String) this.getArguments()[0];
        addBehaviour(new DerivateConstantEquationBehaviour(otherAgentName));
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
