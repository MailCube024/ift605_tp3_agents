package com.ift605.tp3.jade.agents.client.behaviours;

import com.ift605.tp3.constants.ClientConstants;
import jade.core.behaviours.Behaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Michaël on 11/16/2015.
 */
public class ClientListenerBehaviour extends Behaviour {

    private final GuiAgent agent;

    public ClientListenerBehaviour(GuiAgent agent){
        this.agent = agent;
    }

    @Override
    public void action() {
        listen(agent,this, MessageTemplate.MatchPerformative(ACLMessage.INFORM)).forRequest(response -> {
            GuiEvent ge = new GuiEvent(this, ClientConstants.RESPONSE);
            ge.addParameter(response.getEquation());
            agent.postGuiEvent(ge);
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
