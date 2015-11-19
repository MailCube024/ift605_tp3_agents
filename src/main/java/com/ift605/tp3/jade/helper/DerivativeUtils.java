package com.ift605.tp3.jade.helper;

import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class DerivativeUtils {

    public static double calculateDerivate(Equation equation, double x, double epsilon){
        return (equation.getFunctionValue(x + epsilon) - equation.getFunctionValue(x)) / ((x + epsilon) - x);
    }

    public static double diffDerivate(Equation initial, Equation derivate, double x, double epsilon){
        return calculateDerivate(initial,x,epsilon) - derivate.getFunctionValue(x);
    }
}
