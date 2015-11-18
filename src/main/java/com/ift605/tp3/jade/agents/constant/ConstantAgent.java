package com.ift605.tp3.jade.agents.constant;

import com.ift605.tp3.jade.agents.basic.behaviors.DerivateBasicEquationBehaviour;
import com.ift605.tp3.jade.agents.constant.behaviors.DerivateConstantEquationBehaviour;
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
public class ConstantAgent extends Agent {
    private static final Logger logger = LoggerFactory.getLogger(ConstantAgent.class);

    @Override
    public void setup() {
        try {
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());

            ServiceDescription sd = new ServiceDescription();
            sd.setName("ConstantAgent");
            sd.setType("Derivation");
            dfd.addServices(sd);
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.out.println(e.getACLMessage());
        }
//
//        final String otherAgentName = (String) this.getArguments()[0];
//        addBehaviour(new DerivateConstantEquationBehaviour(otherAgentName));
        addBehaviour(new DerivateConstantEquationBehaviour());
        logger.info("Constant Agent Running!");

    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
