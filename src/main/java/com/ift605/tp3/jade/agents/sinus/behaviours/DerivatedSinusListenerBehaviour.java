package com.ift605.tp3.jade.agents.sinus.behaviours;

import com.ift605.tp3.jade.agents.sinus.SinusEquationAgent;
import com.ift605.tp3.jade.agents.summative.SummativeEquationAgent;
import com.ift605.tp3.jade.messages.EquationBinding;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.SinusEquation;

import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michaël on 11/18/2015.
 */
public class DerivatedSinusListenerBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivatedSinusListenerBehaviour.class);

    private final SinusEquationAgent agent;

    public DerivatedSinusListenerBehaviour(SinusEquationAgent sinusEquationAgent) {
        this.agent = sinusEquationAgent;
    }

    @Override
    public void action() {
        listen(myAgent, this, MessageTemplate.MatchPerformative(ACLMessage.INFORM)).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            AbstractEquation equation = (AbstractEquation) binding.getOriginalEquation();
            agent.putEquation(equation.getUserReadableString(), (AbstractEquation) binding.getResultEquation());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
