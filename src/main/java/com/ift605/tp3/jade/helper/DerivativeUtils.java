package com.ift605.tp3.jade.helper;

import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class DerivativeUtils {

    public static double calculateDerivate(Equation equation, double x, double epsilon){
        return (equation.getFunctionValue(x + epsilon) - equation.getFunctionValue(x)) / epsilon;
    }

    public static double diffDerivate(Equation initial, Equation derivate, double x, double epsilon){
        return Math.abs(calculateDerivate(initial,x,epsilon) - derivate.getFunctionValue(x));
    }

    public static double ratioDerivate(Equation initial, Equation derivate, double x, double epsilon){
        double initialDerivate = calculateDerivate(initial,x,epsilon);
        double calculatedDerivate = derivate.getFunctionValue(x);

        if(initialDerivate == calculatedDerivate)
            return 0;

        if(calculatedDerivate == 0)
            return Double.MAX_VALUE;

        return Math.abs(1 - Math.abs(calculateDerivate(initial,x,epsilon) / derivate.getFunctionValue(x)));
    }
}
