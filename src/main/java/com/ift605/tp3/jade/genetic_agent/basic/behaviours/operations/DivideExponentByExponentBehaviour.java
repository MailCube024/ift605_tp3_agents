package com.ift605.tp3.jade.genetic_agent.basic.behaviours.operations;

import com.ift605.tp3.jade.genetic_agent.behaviours.contract.Operation;
import udes.ds.agent.BasicEquation;
import udes.ds.agent.Equation;

/**
 * Created by Benoit on 2015-11-19.
 */
public class DivideExponentByExponentBehaviour extends Operation {
    @Override
    public Equation apply(Equation equation) {
        BasicEquation eq = (BasicEquation) equation;
        BasicEquation newEq = new BasicEquation(eq.getCoefficient(), 1);
        return newEq;
    }
}
