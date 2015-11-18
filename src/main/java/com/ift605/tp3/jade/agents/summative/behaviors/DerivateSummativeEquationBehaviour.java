package com.ift605.tp3.jade.agents.summative.behaviors;

import com.ift605.tp3.jade.helper.DispatcherFinder;
import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.SummativeEquation;

import java.util.HashMap;
import java.util.Map;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageBuilder.request;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateSummativeEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateSummativeEquationBehaviour.class);

    private static Map<String, AbstractEquation> derivateMap = new HashMap<>();

    @Override
    public void action() {
        listen(myAgent, this).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();

            if(equationMessage.getPerformative() == ACLMessage.REQUEST) {
                SummativeEquation summative = (SummativeEquation) binding.getOriginalEquation();
                logger.info("Received summative equation to derivate: " + summative.getUserReadableString());

                if(derivateMap.containsKey(summative.getFirst().getUserReadableString())
                        && derivateMap.containsKey(summative.getSecond().getUserReadableString())) {
                    AbstractEquation first = derivateMap.get(summative.getFirst().getUserReadableString());
                    AbstractEquation second = derivateMap.get(summative.getSecond().getUserReadableString());

                    SummativeEquation derivated = new SummativeEquation(first, second);
                    logger.info("Derivated summative equation to:" + derivated.getUserReadableString());

                    binding.setResultEquation(derivated);
                    myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
                } else {
                    myAgent.send(request().to(DispatcherFinder.getDispatcherAID(myAgent)).withContent(new EquationMessage(myAgent.getAID(), summative.getFirst())).build());
                    myAgent.send(request().to(DispatcherFinder.getDispatcherAID(myAgent)).withContent(new EquationMessage(myAgent.getAID(), summative.getSecond())).build());
                    myAgent.send(request().to(myAgent.getAID()).withContent(equationMessage).build());
                }
            } else {
                AbstractEquation equation = (AbstractEquation) binding.getOriginalEquation();
                derivateMap.put(equation.getUserReadableString(), (AbstractEquation) binding.getResultEquation());
                block();
            }
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
