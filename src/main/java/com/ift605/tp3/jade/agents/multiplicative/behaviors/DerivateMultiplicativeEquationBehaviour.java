package com.ift605.tp3.jade.agents.multiplicative.behaviors;

import com.ift605.tp3.jade.agents.multiplicative.MultiplicativeEquationAgent;
import com.ift605.tp3.jade.helper.DispatcherFinder;
import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
import com.sun.xml.internal.org.jvnet.staxex.NamespaceContextEx;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.management.resources.agent;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.MultiplicativeEquation;
import udes.ds.agent.SummativeEquation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageBuilder.request;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class DerivateMultiplicativeEquationBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(DerivateMultiplicativeEquationBehaviour.class);
    private final MultiplicativeEquationAgent agent;

    public DerivateMultiplicativeEquationBehaviour(MultiplicativeEquationAgent multiplicativeEquationAgent) {
        this.agent = multiplicativeEquationAgent;
    }

    @Override
    public void action() {
        listen(agent, this, MessageTemplate.MatchPerformative(ACLMessage.REQUEST)).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            MultiplicativeEquation multiplicative = (MultiplicativeEquation) binding.getOriginalEquation();
            logger.info("Received summative equation to derivate: " + multiplicative.getUserReadableString());

            if (agent.containsEquation(multiplicative.getFirst().getUserReadableString())
                    && agent.containsEquation(multiplicative.getSecond().getUserReadableString())) {

                MultiplicativeEquation first = new MultiplicativeEquation(agent.getEquation(multiplicative.getFirst().getUserReadableString()), multiplicative.getSecond());
                MultiplicativeEquation second = new MultiplicativeEquation(multiplicative.getFirst(), agent.getEquation(multiplicative.getSecond().getUserReadableString()));

                SummativeEquation derivated = new SummativeEquation(first, second);
                logger.info("Derivated multiplicative equation to:" + derivated.getUserReadableString());

                binding.setResultEquation(derivated);
                agent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
            } else {
                agent.send(request().to(DispatcherFinder.getDispatcherAID(agent)).withContent(new EquationMessage(agent.getAID(), multiplicative.getFirst())).build());
                agent.send(request().to(DispatcherFinder.getDispatcherAID(agent)).withContent(new EquationMessage(agent.getAID(), multiplicative.getSecond())).build());
                agent.send(request().to(agent.getAID()).withContent(equationMessage).build());
            }
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
