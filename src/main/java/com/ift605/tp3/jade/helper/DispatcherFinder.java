package com.ift605.tp3.jade.helper;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.util.leap.Iterator;

/**
 * Created by Michaël on 11/17/2015.
 */
public class DispatcherFinder {
    private static AID dispatcherAID;

    public static AID getDispatcherAID(Agent agent) {
        return dispatcherAID == null ? (dispatcherAID = searchDFAgent(agent)) : dispatcherAID;
    }

    private static AID searchDFAgent(Agent agent) {
        try {
            ServiceDescription templateSd = new ServiceDescription();
            templateSd.setType("Dispatch");
            templateSd.setName("DispatchAgent");

            DFAgentDescription template = new DFAgentDescription();
            template.addServices(templateSd);

            DFAgentDescription[] results = DFService.search(agent, template);
            if (results.length > 0) {
                return results[0].getName();
            }
        } catch (FIPAException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
