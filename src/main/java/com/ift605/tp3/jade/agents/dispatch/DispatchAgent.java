package com.ift605.tp3.jade.agents.dispatch;

import com.ift605.tp3.jade.agents.dispatch.behaviors.InformDispatchBehaviour;
import com.ift605.tp3.jade.agents.dispatch.behaviors.RequestDispatchBehaviour;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Bruno-Pier on 2015-11-16.
 */
public class DispatchAgent extends Agent{
    private static final Logger logger = LoggerFactory.getLogger(InformDispatchBehaviour.class);

    @Override
    public void setup() {
        try{
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());

            ServiceDescription sd = new ServiceDescription();
            sd.setName("DispatchAgent");
            sd.setType("Dispatch");
            dfd.addServices(sd);
            DFService.register(this, dfd);
        } catch (FIPAException e) {
            System.out.println(e.getACLMessage());
        }

        addBehaviour(new RequestDispatchBehaviour());
        addBehaviour(new InformDispatchBehaviour());
    }

    @Override
    public void takeDown() {
        super.takeDown();
    }
}
