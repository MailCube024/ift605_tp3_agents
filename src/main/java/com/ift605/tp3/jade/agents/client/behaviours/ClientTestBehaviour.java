package com.ift605.tp3.jade.agents.client.behaviours;

import com.ift605.tp3.jade.messages.EquationMessage;
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
 * Created by Michaël on 11/16/2015.
 */
public class ClientTestBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(ClientTestBehaviour.class);

    private final String constantAgentName;
    private final String basicAgentName;
    private State state;

    public ClientTestBehaviour(String basicAgentName, String constantAgentName) {
        this.basicAgentName = basicAgentName;
        this.constantAgentName = constantAgentName;
        this.state = State.BASIC;
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
        logger.info("Sending basic equation to derivation");
        myAgent.send(request().toLocal(basicAgentName).withContent(new EquationMessage(myAgent.getAID(), new BasicEquation(3, 4))).build());
        listen(myAgent, this).forRequest((derivated) -> {
            logger.info("Received " + ((AbstractEquation) derivated.getEquation()).getUserReadableString());
            state = State.CONSTANT;
        });
    }

    private void sendConstantEquation() {
        logger.info("Sending constant equation to derivation");
        myAgent.send(request().toLocal(constantAgentName).withContent(new EquationMessage(myAgent.getAID(), new Constant(3))).build());
        listen(myAgent, this).forRequest((derivated) -> {
            logger.info("Received " + ((AbstractEquation) derivated.getEquation()).getUserReadableString());
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
