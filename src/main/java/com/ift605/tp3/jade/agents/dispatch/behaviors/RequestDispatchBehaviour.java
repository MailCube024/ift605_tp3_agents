package com.ift605.tp3.jade.agents.dispatch.behaviors;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ift605.tp3.jade.messages.MessageBuilder.request;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Bruno-Pier on 2015-11-16.
 */
public class RequestDispatchBehaviour extends Behaviour {
    private static final Logger logger = LoggerFactory.getLogger(InformDispatchBehaviour.class);

    @Override
    public void action() {
        listen(myAgent, this, MessageTemplate.MatchPerformative(ACLMessage.REQUEST)).forEquation(equation -> {
            String equationClass = equation.getClass().getSimpleName();
            logger.info("Request to derivate equation type : " + equationClass);
            if(searchDFAgent(equationClass) != null) {
                logger.info("Search found an agent forr this class : " + equationClass);
                myAgent.send(request().to(searchDFAgent(equationClass)).withContent(equation).build());
            }
            else {
                logger.info("Message dropped : Unknown equation class");
            }
        });
    }

    @Override
    public boolean done() {
        return false;
    }

    private AID searchDFAgent(String equationClassType){
        try{
            ServiceDescription templateSd = new ServiceDescription();
            templateSd.setType("Derivation");

            DFAgentDescription template = new DFAgentDescription();
            template.addServices(templateSd);

            DFAgentDescription[] results = DFService.search(myAgent, template);
            if(results.length > 0){
                for(DFAgentDescription dfd : results){
                    Iterator it = dfd.getAllServices();
                    while (it.hasNext()) {
                        ServiceDescription sd = (ServiceDescription) it.next();
                        if(sd.getName().equals(equationClassType))
                            return dfd.getName();
                    }
                }

                logger.info("Found agent class : " + equationClassType);
            }
        } catch (FIPAException ex) {
            logger.error("Exception : ", ex);
        }
        return null;
    }
}