package com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.StageBehaviour;
import com.ift605.tp3.jade.helper.DerivativeUtils;
import jade.core.behaviours.CyclicBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Michaël on 11/19/2015.
 */
public abstract class OperationBehaviour extends CyclicBehaviour {

    private StageBehaviour myParent;

    protected OperationBehaviour(){
        myParent = (StageBehaviour) parent;
    }

    protected Equation getParentEquation(){
        return myParent.getStartingEquation();
    }

    protected void verifyResult(Equation a, Equation b) {
        double diff = DerivativeUtils.diffDerivate(a, b, 2, 0.01);

        if (diff < 0.01) {
            myParent.setClosestDiff(diff);
            myParent.setBestOperationBehaviour(this);
            myParent.setResultEquation(b);
            //TODO: Stop iterations & send answer to client
            //myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
        } else if (myParent.getClosestDiff() > diff) {
            myParent.setClosestDiff(diff);
            myParent.setBestOperationBehaviour(this);
            myParent.setResultEquation(b);
        } else {
            myParent.removeSubBehaviour(this);
        }
    }
}
