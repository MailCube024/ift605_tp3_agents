/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;
import udes.ds.agent.BasicEquation;

/**
 *
 * @author Michaël
 */
public class BasicParser implements IEquationParser {

    @Override
    public AbstractEquation ParseEquation(String entry) {
        double coefficient = Double.parseDouble(entry.substring(0, entry.indexOf("x^")).trim());
        int exponent = Integer.parseInt(entry.substring(entry.indexOf("x^") + 2).trim());
        
        return new BasicEquation(coefficient, exponent);
    }

}
