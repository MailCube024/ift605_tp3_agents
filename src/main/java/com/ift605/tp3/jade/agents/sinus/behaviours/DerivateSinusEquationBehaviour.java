package com.ift605.tp3.jade.agents.sinus.behaviours;

import com.ift605.tp3.jade.agents.sinus.SinusEquationAgent;
import com.ift605.tp3.jade.helper.DispatcherFinder;
import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.*;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageBuilder.request;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michaël on 11/17/2015.
 */
public class DerivateSinusEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateSinusEquationBehaviour.class);
    private final SinusEquationAgent agent;

    public DerivateSinusEquationBehaviour(SinusEquationAgent sinusEquationAgent) {
        this.agent = sinusEquationAgent;
    }

    @Override
    public void action() {
        listen(agent, this, MessageTemplate.MatchPerformative(ACLMessage.REQUEST)).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            SinusEquation summative = (SinusEquation) binding.getStartingEquation();
            logger.info("Received summative equation to derivate: " + summative.getUserReadableString());

            if (agent.containsEquation(summative.getInside().getUserReadableString())) {
                AbstractEquation content = agent.getEquation(summative.getInside().getUserReadableString());

                CosinusEquation derivated = new CosinusEquation(content);
                logger.info("Derivated sinus equation to:" + derivated.getUserReadableString());

                binding.setResultEquation(derivated);
                agent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
            } else {
                agent.send(request().to(DispatcherFinder.getDispatcherAID(agent)).withContent(new EquationMessage(agent.getAID(), summative.getInside())).build());
                agent.send(request().to(agent.getAID()).withContent(equationMessage).build());
            }
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
