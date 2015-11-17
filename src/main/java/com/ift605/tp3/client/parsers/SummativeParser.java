/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;
import udes.ds.agent.SummativeEquation;

/**
 *
 * @author Michaël
 */
public class SummativeParser implements IEquationParser {

    private static final EquationParser parser = new EquationParser();
    
    @Override
    public AbstractEquation ParseEquation(String entry) {
        AbstractEquation first = parser.ParseEquation(entry.substring(0, entry.indexOf("+")).trim());
        AbstractEquation second = parser.ParseEquation(entry.substring(entry.indexOf("+") + 1).trim());

        return new SummativeEquation(first, second);
    }

}
