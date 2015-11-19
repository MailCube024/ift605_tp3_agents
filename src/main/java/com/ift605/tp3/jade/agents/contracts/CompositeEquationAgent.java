package com.ift605.tp3.jade.agents.contracts;

import jade.core.Agent;
import udes.ds.agent.AbstractEquation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Michaël on 11/18/2015.
 */
public abstract class CompositeEquationAgent extends Agent {
    private Map<String, AbstractEquation> derivateMap = new HashMap<>();

    public boolean containsEquation(String key){
        return derivateMap.containsKey(key);
    }

    public AbstractEquation getEquation(String key){
        return derivateMap.get(key);
    }

    public AbstractEquation putEquation(String key, AbstractEquation equation){
        return derivateMap.put(key,equation);
    }
}
