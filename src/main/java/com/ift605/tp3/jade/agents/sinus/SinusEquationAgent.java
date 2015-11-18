package com.ift605.tp3.jade.agents.sinus;

import com.ift605.tp3.jade.agents.sinus.behaviours.DerivateSinusEquationBehaviour;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Michaël on 11/17/2015.
 */
public class SinusEquationAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(SinusEquationAgent.class);

    @Override
    public void setup() {
        try{
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());

            ServiceDescription sd = new ServiceDescription();
            sd.setName("SinusEquationAgent");
            sd.setType("Derivation");
            dfd.addServices(sd);
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.out.println(e.getACLMessage());
        }

        addBehaviour(new DerivateSinusEquationBehaviour());

        logger.info("Sinus agent started");
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
