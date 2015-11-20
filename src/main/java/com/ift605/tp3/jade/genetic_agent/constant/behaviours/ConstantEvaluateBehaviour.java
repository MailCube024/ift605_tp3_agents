package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.behaviours.EvaluateBehaviour;

/**
 * Created by Michaël on 11/19/2015.
 */
public class ConstantEvaluateBehaviour extends EvaluateBehaviour {
    @Override
    protected void setOperations() {
        operations = new ConstantLearningBehaviour(result);
    }
}


