package com.cc.downunder.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public abstract class StateTerritory {

    private String capital;
    private int population;


    public StateTerritory(String capital, int population) {
        this.capital = capital;
        this.population = population;
    }

    public String getCapital() {
        return capital;
    }

    public int getPopulation() {
        return population;
    }
}
