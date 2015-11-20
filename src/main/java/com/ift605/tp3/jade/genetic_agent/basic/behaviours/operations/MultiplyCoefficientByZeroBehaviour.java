package com.ift605.tp3.jade.genetic_agent.basic.behaviours.operations;

import com.ift605.tp3.jade.genetic_agent.behaviours.contract.Operation;
import udes.ds.agent.BasicEquation;
import udes.ds.agent.Equation;

/**
 * Created by Benoit on 2015-11-19.
 */
public class MultiplyCoefficientByZeroBehaviour extends Operation {
    @Override
    public Equation apply(Equation equation) {
        BasicEquation eq = (BasicEquation) equation;
        BasicEquation newEq = new BasicEquation(0, eq.getExponent());
        return newEq;
    }
}
