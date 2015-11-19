package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

import com.ift605.tp3.jade.genetic_agent.constant.behaviours.contract.OperationBehaviour;
import com.ift605.tp3.jade.helper.DerivativeUtils;
import com.ift605.tp3.jade.messages.EquationBinding;
import com.ift605.tp3.jade.messages.EquationMessage;
import jade.core.behaviours.CyclicBehaviour;
import udes.ds.agent.Constant;

import static com.ift605.tp3.jade.messages.MessageBuilder.inform;
import static com.ift605.tp3.jade.messages.MessageReceiver.listen;

/**
 * Created by Utilisateur on 2015-11-18.
 */
public class AddOneBehaviour extends OperationBehaviour {

    public AddOneBehaviour(){
        super();
    }

    @Override
    public void action() {
        Constant eq = (Constant) getParentEquation();
        Constant newEq = new Constant(eq.getValue() + 1);
        verifyResult(eq, newEq);
    }
}
