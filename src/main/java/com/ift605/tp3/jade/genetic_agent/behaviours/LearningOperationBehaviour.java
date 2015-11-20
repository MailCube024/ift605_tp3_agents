package com.ift605.tp3.jade.genetic_agent.behaviours;

import com.ift605.tp3.jade.genetic_agent.behaviours.contract.Operation;
import com.ift605.tp3.jade.helper.DerivativeUtils;
import jade.core.behaviours.OneShotBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Michaël on 11/19/2015.
 */
public class LearningOperationBehaviour extends OneShotBehaviour {
    private final Operation operation;

    public LearningOperationBehaviour(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void action() {
        LearningBehaviour myParent = (LearningBehaviour) getParent();
        Equation original = myParent.getOriginalEquation();
        Equation modified = operation.apply(myParent.getStartingEquation());
        double diff = DerivativeUtils.ratioDerivate(original, modified, 2, Math.pow(10, -10));
        double diff2 = DerivativeUtils.ratioDerivate(original, modified, 100, Math.pow(10, -10));
        double diff3 = DerivativeUtils.ratioDerivate(original, modified, 1000, Math.pow(10, -10));
        diff = (diff + diff2 + diff3) / 3;

        if (myParent.getClosestDiff() > diff) {
            myParent.setClosestDiff(diff);
            myParent.setBestOperation(operation);
            myParent.setResultEquation(modified);
        }
    }
}
