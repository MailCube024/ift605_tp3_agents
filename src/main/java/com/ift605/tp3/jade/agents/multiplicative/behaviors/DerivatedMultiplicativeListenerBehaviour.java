package com.ift605.tp3.jade.agents.multiplicative.behaviors;

import com.ift605.tp3.jade.agents.multiplicative.MultiplicativeEquationAgent;
import com.ift605.tp3.jade.messages.EquationBinding;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;

import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michaël on 11/18/2015.
 */
public class DerivatedMultiplicativeListenerBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivatedMultiplicativeListenerBehaviour.class);

    private final MultiplicativeEquationAgent agent;

    public DerivatedMultiplicativeListenerBehaviour(MultiplicativeEquationAgent multiplicativeEquationAgent) {
        this.agent = multiplicativeEquationAgent;
    }

    @Override
    public void action() {
        listen(myAgent, this, MessageTemplate.MatchPerformative(ACLMessage.INFORM)).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            AbstractEquation equation = (AbstractEquation) binding.getStartingEquation();
            agent.putEquation(equation.getUserReadableString(), (AbstractEquation) binding.getResultEquation());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
