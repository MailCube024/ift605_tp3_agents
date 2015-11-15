package com.ift605.tp3.jade.messages;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import udes.ds.agent.Equation;

/**
 * Created by Michael on 2015-11-08.
 */
public class MessageReceiver {
    private final Agent agent;
    private final Behaviour behavior;

    private MessageReceiver(Agent agent, Behaviour behavior) {
        this.agent = agent;
        this.behavior = behavior;
    }

    public static MessageReceiver listen(Agent agent, Behaviour behavior) {
        return new MessageReceiver(agent, behavior);
    }

    public void forInteger(IntegerMessageContentReceiver contentReceiver) {
        ACLMessage message = agent.receive();
        if (message != null) {
            contentReceiver.onMessage(Integer.valueOf(message.getContent()));
        } else {
            behavior.block();
        }
    }

    public void forEquation(DerivateEquationContentReceiver contentReceiver) {
        ACLMessage message = agent.receive();
        if (message != null) {
            try {
                contentReceiver.onMessage((Equation) message.getContentObject());
            } catch (UnreadableException e) {
                e.printStackTrace();
            }

        } else
            behavior.block();
    }
}
