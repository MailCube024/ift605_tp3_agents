package com.ift605.tp3.jade.messages;

import udes.ds.agent.Equation;

import java.io.Serializable;

/**
 * Created by Benoit on 2015-11-18.
 */
public class EquationBinding implements Serializable {
    private Equation originalEquation;
    private Equation resultEquation;

    public EquationBinding(Equation originalEquation, Equation resultEquation) {
        this.originalEquation = originalEquation;
        this.resultEquation = resultEquation;
    }

    public Equation getStartingEquation() {
        return originalEquation;
    }

    public void setOriginalEquation(Equation originalEquation) {
        this.originalEquation = originalEquation;
    }

    public Equation getResultEquation() {
        return resultEquation;
    }

    public void setResultEquation(Equation resultEquation) {
        this.resultEquation = resultEquation;
    }
}
