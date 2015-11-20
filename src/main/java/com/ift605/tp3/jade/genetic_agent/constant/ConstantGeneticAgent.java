package com.ift605.tp3.jade.genetic_agent.constant;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.ReceptionBehaviour;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class ConstantGeneticAgent extends Agent{
    private static final Logger logger = LoggerFactory.getLogger(ConstantGeneticAgent.class);

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

        addBehaviour(new ReceptionBehaviour());
        logger.info("Constant Genetic Agent Running!");
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
