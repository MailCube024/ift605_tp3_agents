package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.Operation;
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
public class EvaluateBehaviour extends SequentialBehaviour {
    private static final Logger logger = LoggerFactory.getLogger(EvaluateBehaviour.class);

    private Equation toDerivate;
    private Equation result;

    private LearningBehaviour operations;
    private AID requester;

    public void setRequestInformation(Equation startingEquation, AID sender) {
        toDerivate = startingEquation;
        result = toDerivate;
        this.requester = sender;
    }

    public Equation getResultingEquation() {
        return result;
    }

    public void setResultingEquation(Equation resultingEquation) {
        this.result = resultingEquation;
        double diff = DerivativeUtils.diffDerivate(toDerivate, result, 10, 0.01);
        if (diff < 0.01) {
            // Found a solution - Inform requester
            SendResult();
            reset();
            block();
        }
    }

    @Override
    public void onStart() {
        if (toDerivate == null) block();

        result = toDerivate;
        super.onStart();
    }

    @Override
    public int onEnd() {
        // If we added an operations behaviour to learn derivation
        if (operations != null) {
            logger.info("Removing learning behaviour. Collecting prospect behaviour");
            removeSubBehaviour(operations);
            Operation best = operations.getBestOperation();
            addSubBehaviour(new EvaluateOperationBehaviour(best));
            logger.info("Adding behavior of type: " + best.getClass().getSimpleName());

            result = operations.getResultEquation();
            logger.info("Temporary best equation: " + result.getUserReadableString());
        }

        //Check if result equation gives correct derivation
        double diff = DerivativeUtils.diffDerivate(toDerivate, result, 10, 0.01);
        if (diff < 0.01) {
            // Found a solution - Inform requester
            SendResult();
        } else {
            logger.info("Result " + result.getUserReadableString() + " is not close enough to expectation. Restarting process. Difference:(" + diff + ")");
            operations = new LearningBehaviour(result);
            addSubBehaviour(operations);
        }

        //Replace current behaviour in agent behaviour list
        reset();
        myAgent.addBehaviour(this);
        return super.onEnd();
    }

    private void SendResult() {
        logger.info("Found an equation " + result.getUserReadableString() + "! Sending message to client");
        myAgent.send(inform().to(requester).withContent(new EquationMessage(requester, new EquationBinding(toDerivate, result))).build());
        toDerivate = null;
        result = null;
    }
}


