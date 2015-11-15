package com.ift605.tp3.jade.agents.basic.behaviors;

import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.BasicEquation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateBasicEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateBasicEquationBehaviour.class);
    private final String otherAgent;

    public DerivateBasicEquationBehaviour() {
        otherAgent = "";
    }

    public DerivateBasicEquationBehaviour(String otherAgentName) {
        this.otherAgent = otherAgentName;
    }

    @Override
    public void action() {
        listen(myAgent, this).forEquation(equation -> {
            BasicEquation basic = (BasicEquation) equation;
            logger.info("Received basic equation to derivate: " + basic.getUserReadableString());

            BasicEquation derivated = new BasicEquation(basic.getCoefficient() * basic.getExponent(), basic.getExponent() - 1);
            logger.info("Derivated basic equation to: " + derivated.getUserReadableString());

            myAgent.send(inform().toLocal(otherAgent).withContent(derivated).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
