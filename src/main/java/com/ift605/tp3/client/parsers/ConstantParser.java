/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;
import udes.ds.agent.Constant;

/**
 *
 * @author Michaël
 */
public class ConstantParser implements IEquationParser {

    @Override
    public AbstractEquation ParseEquation(String entry) {
        return new Constant(Double.parseDouble(entry.trim()));
    }
    
}
