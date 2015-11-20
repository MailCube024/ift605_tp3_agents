package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.Operation;
import com.ift605.tp3.jade.helper.DerivativeUtils;
import jade.core.behaviours.OneShotBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Michaël on 11/19/2015.
 */
public class LearningOperationBehaviour extends OneShotBehaviour {
    private final Operation operation;

    protected LearningOperationBehaviour(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void action() {
        LearningBehaviour myParent = (LearningBehaviour) getParent();
        Equation original = myParent.getStartingEquation();
        Equation modified = operation.apply(original);
        double diff = DerivativeUtils.diffDerivate(original, modified, 2, 0.01);

        if (myParent.getClosestDiff() > diff) {
            myParent.setClosestDiff(diff);
            myParent.setBestOperation(operation);
            myParent.setResultEquation(modified);
        }
    }

}
