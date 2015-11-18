package com.ift605.tp3.jade.agents.client.behaviours;

import com.ift605.tp3.constants.ClientConstants;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.gui.GuiAgent;
import jade.gui.GuiEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michaël on 11/17/2015.
 */
public class ClientCancelRequestWakerBehaviour extends WakerBehaviour {
    private static Logger logger = LoggerFactory.getLogger(ClientCancelRequestWakerBehaviour.class);

    private final GuiAgent agent;

    public ClientCancelRequestWakerBehaviour(GuiAgent a, long timeout) {
        super(a, timeout);
        this.agent = a;
    }

    @Override
    protected void onWake() {
        logger.info("Wake up client. Cancel response for equation.");
        GuiEvent ge = new GuiEvent(this, ClientConstants.NORESPONSE);
        agent.postGuiEvent(ge);
    }
}
