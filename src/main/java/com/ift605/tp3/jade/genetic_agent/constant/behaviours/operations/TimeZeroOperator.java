package com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations;

import com.ift605.tp3.jade.genetic_agent.behaviours.contract.Operation;
import udes.ds.agent.Constant;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class TimeZeroOperator extends Operation {
    @Override
    public Equation apply(Equation equation) {
        Constant newEq = new Constant(((Constant) equation).getValue() * 0);
        return newEq;
    }
}
