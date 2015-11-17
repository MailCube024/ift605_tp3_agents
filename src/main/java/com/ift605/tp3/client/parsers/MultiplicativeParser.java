/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;
import udes.ds.agent.MultiplicativeEquation;

/**
 *
 * @author Michaël
 */
public class MultiplicativeParser implements IEquationParser{

    private static final EquationParser parser = new EquationParser();
    
    @Override
    public AbstractEquation ParseEquation(String entry) {
        int firstIndexOpen = entry.indexOf("(");
        int secondIndexOpen = entry.indexOf("(", firstIndexOpen+1);
        int thirdIndexOpen = entry.indexOf("(", secondIndexOpen+1);
        int firstIndexClose = entry.indexOf(")");
        int lastIndexClose = entry.lastIndexOf(")");
        
        if (secondIndexOpen == -1 || lastIndexClose == -1)
            return null;
        
        if(firstIndexOpen < firstIndexClose 
                && firstIndexClose < secondIndexOpen 
                && secondIndexOpen < lastIndexClose)
        {
            if (thirdIndexOpen != -1)
            {
                AbstractEquation first = parser.ParseEquation(entry.substring(firstIndexOpen+1, firstIndexClose).trim());
                AbstractEquation second = parser.ParseEquation(entry.substring(secondIndexOpen, lastIndexClose+1).trim());
                
                return new MultiplicativeEquation(first, second);
            }
            else
            {
                AbstractEquation first = parser.ParseEquation(entry.substring(firstIndexOpen+1, firstIndexClose).trim());
                AbstractEquation second = parser.ParseEquation(entry.substring(secondIndexOpen+1, lastIndexClose).trim());
                
                return new MultiplicativeEquation(first, second);
            }
        }
        else
            return null;
    }
    
}
