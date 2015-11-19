package com.ift605.tp3.jade.messages;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import udes.ds.agent.Equation;

/**
 * Created by Michael on 2015-11-08.
 */
public class MessageReceiver {
    private final Agent agent;
    private final Behaviour behaviour;
    private MessageTemplate msgTemplate;


    private MessageReceiver(Agent agent, Behaviour behaviour) {
        this.agent = agent;
        this.behaviour = behaviour;
    }

    private MessageReceiver(Agent agent, Behaviour behaviour, MessageTemplate msgTemplate){
        this(agent, behaviour);
        this.msgTemplate = msgTemplate;
    }

    public static MessageReceiver listen(Agent agent, Behaviour behaviour) {
        return new MessageReceiver(agent, behaviour);
    }

    public static MessageReceiver listen(Agent agent, Behaviour behaviour, MessageTemplate msgTemplate) {
        return new MessageReceiver(agent, behaviour, msgTemplate);
    }

    public void forInteger(IntegerMessageContentReceiver contentReceiver) {
        ACLMessage message = agent.receive();
        if (message != null) {
            contentReceiver.onMessage(Integer.valueOf(message.getContent()));
        } else {
            behaviour.block();
        }
    }

    public void forRequest(EquationMessageContentReceiver contentReceiver) {
        ACLMessage message;

        if (msgTemplate == null)
            message = agent.receive();
        else
            message = agent.receive(msgTemplate);

        if (message != null) {
            try {
                contentReceiver.onMessage(new EquationMessage(message.getSender(), (EquationBinding) message.getContentObject()));
            } catch (UnreadableException e) {
                e.printStackTrace();
            }
        } else
            behaviour.block();
    }
}
