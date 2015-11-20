package com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract;

import udes.ds.agent.Constant;
import udes.ds.agent.Equation;

/**
 * Created by Michaël on 11/19/2015.
 */
public abstract class Operation {
    public abstract Equation apply(Equation equation);
}
