package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.messages.EquationBinding;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class StageBehaviour extends SequentialBehaviour{
    private double closestValue;
    private CyclicBehaviour currentBehaviour;
    private EquationBinding equations;

    public StageBehaviour(Equation evalEquation){
        this.closestValue = 0;
        this.currentBehaviour = null;
        this.equations = new EquationBinding(evalEquation, null);

        addSubBehaviour(new AddOneBehaviour());
        addSubBehaviour(new SubstractOneBehaviour());
        addSubBehaviour(new TimeZeroBehaviour());
    }

    public double getClosestValue() {
        return closestValue;
    }

    public void setClosestValue(double closestValue) {
        this.closestValue = closestValue;
    }

    public CyclicBehaviour getCurrentBehaviour() {
        return currentBehaviour;
    }

    public void setCurrentBehaviour(CyclicBehaviour currentBehaviour) {
        this.currentBehaviour = currentBehaviour;
    }

    public EquationBinding getEquations() {
        return equations;
    }

    public Equation getOriginalEquation(){
        return this.equations.getOriginalEquation();
    }

    public Equation getResultEquation(){
        return this.equations.getResultEquation();
    }

    public void setOriginalEquation(Equation equation) {
        this.equations.setOriginalEquation(equation);
    }
    public void setResultEquation(Equation equation){
        this.equations.setResultEquation(equation);
    }
}
