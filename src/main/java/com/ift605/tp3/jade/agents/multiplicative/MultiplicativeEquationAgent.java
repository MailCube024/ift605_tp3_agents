package com.ift605.tp3.jade.agents.multiplicative;

import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michael on 2015-11-14.
 */
public class MultiplicativeEquationAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(MultiplicativeEquationAgent.class);

    @Override
    public void setup() {
        try{
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());

            ServiceDescription sd = new ServiceDescription();
            sd.setName("MultiplicativeEquationAgent");
            sd.setType("MultiplicativeEquationAgent");
            dfd.addServices(sd);
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.out.println(e.getACLMessage());
        }
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
