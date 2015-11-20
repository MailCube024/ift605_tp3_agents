package com.ift605.tp3.jade.genetic_agent.behaviours;

import com.ift605.tp3.jade.genetic_agent.behaviours.contract.Operation;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.*;
import jade.core.behaviours.SequentialBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public abstract class LearningBehaviour extends SequentialBehaviour {
    private double closestDiff;
    private Equation equation;
    private Equation resultEquation;
    private Operation bestOperation;

    public LearningBehaviour(Equation equation) {
        this.equation = equation;
        this.closestDiff = Double.MAX_VALUE;
        this.bestOperation = null;
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

    public Equation getOriginalEquation() {
        if(LearningBehaviour.class.isInstance(parent))
            return ((LearningBehaviour)parent).getOriginalEquation();
        else
            return ((EvaluateBehaviour)parent).getOriginalEquation();
    }
}
