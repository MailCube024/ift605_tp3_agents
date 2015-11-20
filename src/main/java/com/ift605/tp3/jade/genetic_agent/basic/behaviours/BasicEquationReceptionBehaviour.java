package com.ift605.tp3.jade.genetic_agent.basic.behaviours;

import com.ift605.tp3.jade.messages.EquationBinding;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Utilisateur on 2015-11-19.
 */
public class BasicEquationReceptionBehaviour extends Behaviour {
    private BasicEquationEvaluateBehaviour evaluateBehaviour;

    @Override
    public void action() {
        listen(myAgent, this, MessageTemplate.MatchPerformative(ACLMessage.REQUEST)).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();
            if (evaluateBehaviour == null) {
                evaluateBehaviour = new BasicEquationEvaluateBehaviour();
                myAgent.addBehaviour(evaluateBehaviour);
            }
            evaluateBehaviour.setRequestInformation(binding.getStartingEquation(), equationMessage.getSender());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
