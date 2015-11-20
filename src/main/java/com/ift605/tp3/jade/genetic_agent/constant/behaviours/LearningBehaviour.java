package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.Operation;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.AddOneOperator;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.SubstractOneOperator;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.TimeZeroOperator;
import jade.core.behaviours.*;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class LearningBehaviour extends SequentialBehaviour {
    private double closestDiff;
    private Equation equation;
    private Equation resultEquation;
    private Operation bestOperation;

    public LearningBehaviour(Equation equation) {
        this.equation = equation;
        this.closestDiff = Integer.MAX_VALUE;
        this.bestOperation = null;

        addSubBehaviour(new LearningOperationBehaviour(new AddOneOperator()));
        addSubBehaviour(new LearningOperationBehaviour(new SubstractOneOperator()));
        addSubBehaviour(new LearningOperationBehaviour(new TimeZeroOperator()));
    }

    public double getClosestDiff() {
        return closestDiff;
    }

    public void setClosestDiff(double closestDiff) {
        this.closestDiff = closestDiff;
    }

    public Operation getBestOperation() {
        return bestOperation;
    }

    public Equation getResultEquation() {
        return resultEquation;
    }

    public Equation getStartingEquation() {
        return equation;
    }

    public void setResultEquation(Equation resultEquation) {
        this.resultEquation = resultEquation;
    }

    public void setBestOperation(Operation bestOperation) {
        this.bestOperation = bestOperation;
    }
}
