package com.ift605.tp3.jade.agents.constant.behaviors;

import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.Constant;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateConstantEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateConstantEquationBehaviour.class);

    @Override
    public void action() {
        listen(myAgent, this).forEquation(equation -> {
            Constant constant = (Constant) equation;
            logger.info("Received basic equation to derivate: " + constant.getUserReadableString());

            Constant derivated = new Constant(0);
            logger.info("Derivated constant equation to: " + derivated.getUserReadableString());

            myAgent.send(inform().to().withContent(derivated).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
