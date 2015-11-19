package com.ift605.tp3.jade.agents.summative;

import com.ift605.tp3.jade.agents.contracts.CompositeEquationAgent;
import com.ift605.tp3.jade.agents.summative.behaviors.DerivateSummativeEquationBehaviour;
import com.ift605.tp3.jade.agents.summative.behaviors.DerivatedSummativeListenerBehaviour;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.AbstractEquation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michael on 2015-11-14.
 */
public class SummativeEquationAgent extends CompositeEquationAgent {
    private static final Logger logger = LoggerFactory.getLogger(SummativeEquationAgent.class);

    @Override
    public void setup() {
        try{
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());

            ServiceDescription sd = new ServiceDescription();
            sd.setName("SummativeEquationAgent");
            sd.setType("Derivation");
            dfd.addServices(sd);
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.out.println(e.getACLMessage());
        }

        addBehaviour(new DerivateSummativeEquationBehaviour(this));
        addBehaviour(new DerivatedSummativeListenerBehaviour(this));

        logger.info("Summative agent started");
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
