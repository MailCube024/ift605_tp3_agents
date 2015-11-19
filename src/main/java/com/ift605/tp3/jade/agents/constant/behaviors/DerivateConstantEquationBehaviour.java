package com.ift605.tp3.jade.agents.constant.behaviors;

import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
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
        listen(myAgent, this).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            Constant constant = (Constant) binding.getStartingEquation();
            logger.info("Received basic equation to derivate: " + constant.getUserReadableString());

            Constant derivated = new Constant(0);
            logger.info("Derivated constant equation to: " + derivated.getUserReadableString());

            binding.setResultEquation(derivated);
            myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
