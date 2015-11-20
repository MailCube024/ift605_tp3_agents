package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.behaviours.LearningBehaviour;
import com.ift605.tp3.jade.genetic_agent.behaviours.LearningOperationBehaviour;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.AddOneOperator;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.SubstractOneOperator;
import com.ift605.tp3.jade.genetic_agent.constant.behaviours.operations.TimeZeroOperator;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class ConstantLearningBehaviour extends LearningBehaviour {

    public ConstantLearningBehaviour(Equation equation) {
        super(equation);

        addSubBehaviour(new LearningOperationBehaviour(new AddOneOperator()));
        addSubBehaviour(new LearningOperationBehaviour(new SubstractOneOperator()));
        addSubBehaviour(new LearningOperationBehaviour(new TimeZeroOperator()));
    }
}
