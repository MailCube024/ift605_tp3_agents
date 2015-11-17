/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ift605.tp3.client.parsers;

import udes.ds.agent.AbstractEquation;

/**
 *
 * @author Michaël
 */
public interface IEquationParser {
    public AbstractEquation ParseEquation(String entry);
}
