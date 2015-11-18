package com.ift605.tp3.jade.agents.summative.behaviors;

import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.SummativeEquation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateSummativeEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateSummativeEquationBehaviour.class);

    @Override
    public void action() {
        listen(myAgent, this).forRequest(equationMessage -> {
            SummativeEquation summative = (SummativeEquation) equationMessage.getEquation();
            logger.info("Received summative equation to derivate: " + summative.getUserReadableString());

            //TODO: Request first derivation
            AbstractEquation first = summative.getFirst();
            //TODO: Request second derivation
            AbstractEquation second = summative.getSecond();

            SummativeEquation derivated = new SummativeEquation(first, second);
            logger.info("Derivated summative equation to:" + derivated.getUserReadableString());

            myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), derivated)).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
