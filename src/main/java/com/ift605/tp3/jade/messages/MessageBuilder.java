package com.ift605.tp3.jade.messages;

import com.ift605.tp3.jade.tools.SerializationHelper;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import udes.ds.agent.Equation;

import java.io.IOException;

/**
 * Created by Michael on 2015-11-08.
 */
public class MessageBuilder {

    private final ACLMessage message;

    private MessageBuilder(int performative) {
        this.message = new ACLMessage(performative);
    }

    public static MessageBuilder inform() {
        return new MessageBuilder(ACLMessage.INFORM);
    }

    public static MessageBuilder request() {
        return new MessageBuilder(ACLMessage.REQUEST);
    }

    public MessageBuilder to(AID... receivers) {
        for (AID receiver : receivers) {
            message.addReceiver(receiver);
        }
        return this;
    }

    public MessageBuilder toLocal(String... otherAgentNames) {
        for (String agentName : otherAgentNames) {
            AID address = new AID(agentName, AID.ISLOCALNAME);
            message.addReceiver(address);
        }
        return this;
    }

    public MessageBuilder withContent(String content) {
        message.setContent(content);
        return this;
    }

    public ACLMessage build() {
        return message;
    }

    public MessageBuilder withContent(int i) {
        return withContent(Integer.toString(i));
    }

    public MessageBuilder withContent(Equation eq) {
        try {
            return withContent(new String(SerializationHelper.serialize(eq)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}