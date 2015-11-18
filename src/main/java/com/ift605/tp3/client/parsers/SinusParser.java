/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;
import udes.ds.agent.SinusEquation;

/**
 *
 * @author Utilisateur
 */
public class SinusParser implements IEquationParser{
    
    private static final EquationParser parser = new EquationParser();
    
    @Override
    public AbstractEquation ParseEquation(String entry) {
        int indexOpen = entry.indexOf("(");
        int indexClose = entry.indexOf(")");
        AbstractEquation inside = parser.ParseEquation(entry.substring(indexOpen+1,indexClose).trim());
        
       return new SinusEquation(inside);
    }
    
}
