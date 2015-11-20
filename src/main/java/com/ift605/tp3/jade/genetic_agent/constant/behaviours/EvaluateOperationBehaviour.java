package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.Operation;
import jade.core.behaviours.CyclicBehaviour;

/**
 * Created by Michaël on 11/19/2015.
 */
public class EvaluateOperationBehaviour extends CyclicBehaviour {
    private final Operation operation;

    public EvaluateOperationBehaviour(Operation op){
        this.operation = op;
    }

    @Override
    public void action() {
        EvaluateBehaviour myParent = (EvaluateBehaviour)getParent();
        myParent.setResultingEquation(operation.apply(myParent.getResultingEquation()));
    }
}
