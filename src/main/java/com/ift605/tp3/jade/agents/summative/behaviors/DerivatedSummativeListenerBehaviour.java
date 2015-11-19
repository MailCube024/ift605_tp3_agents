package com.ift605.tp3.jade.agents.summative.behaviors;

import com.ift605.tp3.jade.agents.summative.SummativeEquationAgent;
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
public class DerivatedSummativeListenerBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivatedSummativeListenerBehaviour.class);

    private final SummativeEquationAgent agent;

    public DerivatedSummativeListenerBehaviour(SummativeEquationAgent summativeEquationAgent) {
        this.agent = summativeEquationAgent;
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