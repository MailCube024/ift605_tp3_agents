package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.OperationBehaviour;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.AddOneBehaviour;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.SubstractOneBehaviour;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.TimeZeroBehaviour;
import jade.core.behaviours.*;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class LearningBehaviour extends SequentialBehaviour {
    private double closestDiff;
    private OperationBehaviour bestBehaviour;
    private Equation equation;
    private Equation resultEquation;

    public LearningBehaviour() {
        equation = ((EvaluateBehaviour)getParent()).getResultingEquation();
        this.closestDiff = Integer.MAX_VALUE;
        this.bestBehaviour = null;

        addSubBehaviour(new AddOneBehaviour());
        addSubBehaviour(new SubstractOneBehaviour());
        addSubBehaviour(new TimeZeroBehaviour());
    }

    public double getClosestDiff() {
        return closestDiff;
    }

    public void setClosestDiff(double closestDiff) {
        this.closestDiff = closestDiff;
    }

    public CyclicBehaviour getBestBehaviour() {
        return bestBehaviour;
    }

    public void setBestOperationBehaviour(OperationBehaviour currentBehaviour) {
        this.bestBehaviour = currentBehaviour;
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
}
