package com.ift605.tp3.jade.genetic_agent.basic.behaviours;

import com.ift605.tp3.jade.genetic_agent.behaviours.EvaluateBehaviour;
import com.ift605.tp3.jade.genetic_agent.behaviours.EvaluateOperationBehaviour;
import com.ift605.tp3.jade.genetic_agent.behaviours.LearningBehaviour;
import com.ift605.tp3.jade.genetic_agent.behaviours.contract.Operation;
import com.ift605.tp3.jade.helper.DerivativeUtils;
import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.AID;
import jade.core.behaviours.SequentialBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import udes.ds.agent.Equation;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;

/**
 * Created by Michaël on 11/19/2015.
 */
public class BasicEquationEvaluateBehaviour extends EvaluateBehaviour {

    @Override
    protected void setOperations() {
        operations = new BasicEquationLearningBehaviour(result);
    }
}


