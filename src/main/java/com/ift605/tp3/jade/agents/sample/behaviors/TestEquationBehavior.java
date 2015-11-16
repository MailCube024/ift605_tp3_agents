package com.ift605.tp3.jade.agents.sample.behaviors;

import com.ift605.tp3.jade.tools.ContainerKiller;
import jade.core.behaviours.Behaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.BasicEquation;
import udes.ds.agent.Constant;

import static com.ift605.tp3.jade.messages.MessageBuilder.request;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michael on 2015-11-15.
 */
public class TestEquationBehavior extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(TestEquationBehavior.class);

    private final String constantAgentName;
    private final String basicAgentName;
    private State state;

    public TestEquationBehavior(String basicAgentName, String constantAgentName) {
        this.basicAgentName = basicAgentName;
        this.constantAgentName = constantAgentName;
    }

    @Override
    public void action() {
        switch (state) {
            case BASIC:
                sendBasicEquation();
                break;
            case CONSTANT:
                sendConstantEquation();
                break;
            case DONE:
                close();
                break;
            default:
                block();
        }

    }

    private void sendBasicEquation() {
        myAgent.send(request().toLocal(basicAgentName).withContent(new BasicEquation(3, 4)).build());
        listen(myAgent, this).forEquation((derivated) -> {
            logger.info("Received " + ((AbstractEquation) derivated).getUserReadableString());
            state = State.CONSTANT;
        });
    }

    private void sendConstantEquation() {
        myAgent.send(request().toLocal(constantAgentName).withContent(new Constant(3)).build());
        listen(myAgent, this).forEquation((derivated) -> {
            logger.info("Received " + ((AbstractEquation) derivated).getUserReadableString());
            state = State.DONE;
        });
    }

    private void close() {
        ContainerKiller.killContainerOf(myAgent);
    }

    @Override
    public boolean done() {
        return false;
    }

    private enum State {BASIC, CONSTANT, DONE}
}