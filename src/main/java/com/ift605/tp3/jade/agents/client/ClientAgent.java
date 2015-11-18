package com.ift605.tp3.jade.agents.client;

import com.ift605.tp3.client.DerivationForm;
import com.ift605.tp3.constants.ClientConstants;
import com.ift605.tp3.jade.agents.client.behaviours.ClientListenerBehaviour;
import com.ift605.tp3.jade.helper.DispatcherFinder;
import jade.core.AID;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;
import udes.ds.agent.Equation;

import static com.ift605.tp3.jade.messages.MessageBuilder.request;

/**
 * Created by Michael on 2015-11-14.
 */
public class ClientAgent extends GuiAgent {
    private static final Logger logger = LoggerFactory.getLogger(ClientAgent.class);

    transient protected DerivationForm form;
    private AID dispatcher;

    @Override
    public void setup() {
        logger.info("Client agent started");

        form = new DerivationForm(this);
        form.setVisible(true);

        AID dispatcher = DispatcherFinder.getDispatcherAID(this);

        addBehaviour(new ClientListenerBehaviour(this));
    }

    @Override
    public void takeDown() {
    }

    @Override
    protected void onGuiEvent(GuiEvent guiEvent) {
        int command = guiEvent.getType();
        switch (command) {
            case ClientConstants.DERIVATE:
                logger.info("Derivation command received. Sending to system...");
                Equation eq = (Equation) guiEvent.getParameter(0);
                send(request().to(dispatcher).withContent(eq).build());
                break;
            case ClientConstants.RESPONSE:
                logger.info("Received derivation from system. Sending output");
                AbstractEquation derivated = (AbstractEquation) guiEvent.getParameter(0);
                form.receiveDerivation(derivated);
                break;
            case ClientConstants.SHUTDOWN:
                logger.info("Closing event received. Requesting closure of agent to platform.");
                doDelete();
                break;
            default:
                return;
        }
    }
}
