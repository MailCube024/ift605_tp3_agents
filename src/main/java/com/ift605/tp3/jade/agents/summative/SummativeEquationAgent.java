package com.ift605.tp3.jade.agents.summative;

import com.ift605.tp3.jade.agents.sinus.behaviours.DerivateSinusEquationBehaviour;
import com.ift605.tp3.jade.agents.summative.behaviors.DerivateSummativeEquationBehaviour;
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
public class SummativeEquationAgent extends Agent {
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

        addBehaviour(new DerivateSummativeEquationBehaviour());

        logger.info("Summative agent started");
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
