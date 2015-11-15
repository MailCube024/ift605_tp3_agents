package com.ift605.tp3.jade.messages;

import udes.ds.agent.Equation;

/**
 * Created by Michael on 2015-11-15.
 */
public interface DerivateEquationContentReceiver {
    void onMessage(Equation message);
}
