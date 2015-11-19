package com.ift605.tp3.jade.helper;

import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class DerivativeUtils {

    public static double calculateDerivate(Equation equation, double x, double epsilon){
        return (equation.getFunctionValue(x + epsilon) - equation.getFunctionValue(x)) / ((x + epsilon) - x);
    }

    public static double diffDerivate(Equation equation1, Equation equation2, double x, double epsilon){
        return calculateDerivate(equation1,x,epsilon) - calculateDerivate(equation2,x,epsilon);
    }
}
