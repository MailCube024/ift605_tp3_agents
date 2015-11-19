package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.OperationBehaviour;
import com.ift605.tp3.jade.messages.EquationBinding;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class StageBehaviour extends SequentialBehaviour {
    private double closestDiff;
    private CyclicBehaviour bestBehaviour;
    private EquationBinding equations;

    public StageBehaviour(Equation evalEquation) {
        this.closestDiff = Integer.MAX_VALUE;
        this.bestBehaviour = null;
        this.equations = new EquationBinding(evalEquation, null);

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

    public EquationBinding getEquations() {
        return equations;
    }

    public Equation getStartingEquation() {
        return this.equations.getStartingEquation();
    }

    public Equation getResultEquation() {
        return this.equations.getResultEquation();
    }

    public void setOriginalEquation(Equation equation) {
        this.equations.setOriginalEquation(equation);
    }

    public void setResultEquation(Equation equation) {
        this.equations.setResultEquation(equation);
    }

}
