package com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.LearningBehaviour;
import com.ift605.tp3.jade.helper.DerivativeUtils;
import jade.core.behaviours.CyclicBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Michaël on 11/19/2015.
 */
public abstract class OperationBehaviour extends CyclicBehaviour {
    private LearningBehaviour myParent;

    protected OperationBehaviour() {
        myParent = (LearningBehaviour) parent;
    }

    protected Equation getParentEquation() {
        return myParent.getStartingEquation();
    }

    protected void verifyResult(Equation a, Equation b) {
        double diff = DerivativeUtils.diffDerivate(a, b, 2, 0.01);

        if (myParent.getClosestDiff() > diff) {
            myParent.setClosestDiff(diff);
            myParent.setBestOperationBehaviour(this);
            myParent.setResultEquation(b);
        }
    }
}
