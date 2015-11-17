/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;

/**
 *
 * @author Benoit
 */
public class EquationParser implements IEquationParser {

    private static final MultiplicativeParser multiplicativeParser = new MultiplicativeParser();
    private static final SummativeParser summativeParser = new SummativeParser();
    private static final BasicParser basicParser = new BasicParser();
    private static final ConstantParser constantParser = new ConstantParser();
    private static final SinusParser sinusParser = new SinusParser();
    
    @Override
    public AbstractEquation ParseEquation(String entry) {
        if (entry == null || entry.equals(""))
            return null;
        
        if (entry.contains("sin"))
            return sinusParser.ParseEquation(entry);
        else if (entry.contains("(") && entry.contains(")"))
            return multiplicativeParser.ParseEquation(entry);
        else if (entry.contains("+"))
            return summativeParser.ParseEquation(entry);
        else if (entry.contains("x^"))
            return basicParser.ParseEquation(entry);
        else 
            return constantParser.ParseEquation(entry);
    }
    
}
