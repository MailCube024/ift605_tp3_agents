package com.ift605.tp3.jade.agents.multiplicative.behaviors;

import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.MultiplicativeEquation;
import udes.ds.agent.SummativeEquation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateMultiplicativeEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateMultiplicativeEquationBehaviour.class);

    @Override
    public void action() {
        listen(myAgent, this).forEquation(equation -> {
            MultiplicativeEquation multiplicative = (MultiplicativeEquation) equation;
            logger.info("Received multiplicative equation to derivate: " + multiplicative.getUserReadableString());

            //TODO: Request first derivation
            MultiplicativeEquation first = new MultiplicativeEquation(multiplicative.getFirst(), multiplicative.getSecond());
            //TODO: Request second derivation
            MultiplicativeEquation second = new MultiplicativeEquation(multiplicative.getFirst(), multiplicative.getSecond());

            SummativeEquation derivated = new SummativeEquation(first, second);
            logger.info("Derivated multiplicative equation to:" + derivated.getUserReadableString());

            myAgent.send(inform().to().withContent(derivated).build());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
