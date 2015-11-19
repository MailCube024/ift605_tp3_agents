package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.messages.EquationBinding;
import jade.core.behaviours.Behaviour;

import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Utilisateur on 2015-11-19.
 */
public class ReceptionBehaviour extends Behaviour {
    private StageBehaviour firstStageBehaviour;

    public ReceptionBehaviour() {
        this.firstStageBehaviour = null;
    }

    @Override
    public void action() {
        listen(myAgent, this).forRequest(equationMessage -> {
            EquationBinding binding = equationMessage.getEquation();

            if (firstStageBehaviour == null){
                firstStageBehaviour = new StageBehaviour(binding.getStartingEquation());
                myAgent.addBehaviour(firstStageBehaviour);
            }
            else
                firstStageBehaviour.setOriginalEquation(binding.getStartingEquation());
        });
    }

    @Override
    public boolean done() {
        return false;
    }
}
