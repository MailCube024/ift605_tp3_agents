package com.ift605.tp3.jade.agents.summative.behaviors;

import com.ift605.tp3.jade.agents.summative.SummativeEquationAgent;
import com.ift605.tp3.jade.helper.DispatcherFinder;
import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.SummativeEquation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageBuilder.request;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateSummativeEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateSummativeEquationBehaviour.class);
    private final SummativeEquationAgent agent;

    public DerivateSummativeEquationBehaviour(SummativeEquationAgent summativeEquationAgent) {
        this.agent = summativeEquationAgent;
    }

    @Override
    public void action() {
        listen(agent, this, MessageTemplate.MatchPerformative(ACLMessage.REQUEST)).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            SummativeEquation summative = (SummativeEquation) binding.getStartingEquation();
            logger.info("Received summative equation to derivate: " + summative.getUserReadableString());

            if (agent.containsEquation(summative.getFirst().getUserReadableString())
                    && agent.containsEquation(summative.getSecond().getUserReadableString())) {
                AbstractEquation first = agent.getEquation(summative.getFirst().getUserReadableString());
                AbstractEquation second = agent.getEquation(summative.getSecond().getUserReadableString());

                SummativeEquation derivated = new SummativeEquation(first, second);
                logger.info("Derivated summative equation to:" + derivated.getUserReadableString());

                binding.setResultEquation(derivated);
                agent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
            } else {
                agent.send(request().to(DispatcherFinder.getDispatcherAID(agent)).withContent(new EquationMessage(agent.getAID(), summative.getFirst())).build());
                agent.send(request().to(DispatcherFinder.getDispatcherAID(agent)).withContent(new EquationMessage(agent.getAID(), summative.getSecond())).build());
                agent.send(request().to(agent.getAID()).withContent(equationMessage).build());
            }
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
