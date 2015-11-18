package com.ift605.tp3.jade.agents.basic;

import com.ift605.tp3.jade.agents.basic.behaviors.DerivateBasicEquationBehaviour;
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
public class BasicEquationAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(BasicEquationAgent.class);

    @Override
    public void setup() {
//        try{
//            DFAgentDescription dfd = new DFAgentDescription();
//            dfd.setName(getAID());
//
//            ServiceDescription sd = new ServiceDescription();
//            sd.setName("BasicEquationAgent");
//            sd.setType("Derivation");
//            dfd.addServices(sd);
//            DFService.register(this, dfd);
//        } catch (FIPAException e) {
//            System.out.println(e.getACLMessage());
//        }

        logger.info("Basic Equation Agent Started!");
//        final String otherAgentName = (String) this.getArguments()[0];
//        addBehaviour(new DerivateBasicEquationBehaviour(otherAgentName));

        final String clientAgent = (String) this.getArguments()[0];
        addBehaviour(new DerivateBasicEquationBehaviour());
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
