package com.ift605.tp3.jade.messages;

import jade.core.AID;
import udes.ds.agent.Equation;

/**
 * Created by Utilisateur on 2015-11-16.
 */
public class EquationMessage {
    private AID sender;
    private EquationBinding equation;

    public EquationMessage(AID sender, Equation equation){
        this.sender = sender;
        this.equation = new EquationBinding(equation, null);
    }

    public EquationMessage(AID sender, EquationBinding equation){
        this.sender = sender;
        this.equation = equation;
    }

    public AID getSender() {
        return sender;
    }

    public void setSender(AID sender) {
        this.sender = sender;
    }

    public EquationBinding getEquation() {
        return equation;
    }

    public void setEquation(EquationBinding equation) {
        this.equation = equation;
    }
}
