package com.ift605.tp3.jade.genetic_agent.basic.behaviours;

import com.ift605.tp3.jade.genetic_agent.basic.behaviours.operations.*;
import com.ift605.tp3.jade.genetic_agent.behaviours.LearningBehaviour;
import com.ift605.tp3.jade.genetic_agent.behaviours.LearningOperationBehaviour;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class BasicEquationLearningBehaviour extends LearningBehaviour {

    public BasicEquationLearningBehaviour(Equation equation) {
        super(equation);

        addSubBehaviour(new LearningOperationBehaviour(new AddExponentToCoefficientBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new AddOneToCoefficientBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new AddOneToExponentBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new DivideCoefficientByCoefficientBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new DivideCoefficientByExponentBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new DivideExponentByExponentBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new MultiplyCoefficientByExponentBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new MultiplyCoefficientByZeroBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new MultiplyExponentByZeroBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new SubstractExponentToCoefficientBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new SubstractOneToCoefficientBehaviour()));
        addSubBehaviour(new LearningOperationBehaviour(new SubstractOneToExponentBehaviour()));
    }
}
