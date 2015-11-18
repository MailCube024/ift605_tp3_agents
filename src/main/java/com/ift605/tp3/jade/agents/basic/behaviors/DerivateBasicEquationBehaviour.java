package com.ift605.tp3.jade.agents.basic.behaviors;

import com.ift605.tp3.jade.messages.EquationMessage;
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

    @Override
    public void action() {
        listen(myAgent, this).forRequest(equationMessage -> {
            BasicEquation basic = (BasicEquation) equationMessage.getEquation();
            logger.info("Received basic equation to derivate: " + basic.getUserReadableString());
            logger.info("Type of equation: name (" + equationMessage.getEquation().getClass().getSimpleName() + ")");

            BasicEquation derivated = new BasicEquation(basic.getCoefficient() * basic.getExponent(), basic.getExponent() - 1);
            logger.info("Derivated basic equation to: " + derivated.getUserReadableString());

            myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), derivated)).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
