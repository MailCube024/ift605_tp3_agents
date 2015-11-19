package com.ift605.tp3.jade.genetic_agent.constant.behaviours;

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
public class TimeZeroBehaviour extends CyclicBehaviour {
    @Override
    public void action() {
        StageBehaviour parent = (StageBehaviour) getParent();
        Constant eq = (Constant) parent.getOriginalEquation();
        Constant newEq = new Constant(eq.getValue() * 0);

        double diff = DerivativeUtils.diffDerivate(eq, newEq, 2, 1);

        if(diff == 0){
            parent.setClosestValue(diff);
            parent.setCurrentBehaviour(this);
            parent.setResultEquation(newEq);
            //myAgent.send(inform().to(equationMessage.getSender()).withContent(new EquationMessage(equationMessage.getSender(), binding)).build());
        }
        else if(parent.getCurrentBehaviour() == null || parent.getClosestValue() > diff){
            parent.setClosestValue(diff);
            parent.setCurrentBehaviour(this);
            parent.setResultEquation(newEq);
        }
        else {
            parent.removeSubBehaviour(this);
        }
    }
}
