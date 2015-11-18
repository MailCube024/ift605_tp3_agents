package com.ift605.tp3.jade.agents.sinus.behaviours;

import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.CosinusEquation;
import udes.ds.agent.SinusEquation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michaël on 11/17/2015.
 */
public class DerivateSinusEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateSinusEquationBehaviour.class);

    @Override
    public void action() {
        listen(myAgent, this).forRequest(equationMessage -> {
            SinusEquation sinus = (SinusEquation) equationMessage.getEquation();
            logger.info("Received summative equation to derivate: " + sinus.getUserReadableString());

            //TODO: Derivate content
            AbstractEquation content = sinus.getInside();

            AbstractEquation derivated = new CosinusEquation(content);
            logger.info("Derivated summative equation to:" + derivated.getUserReadableString());

            myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), derivated)).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
