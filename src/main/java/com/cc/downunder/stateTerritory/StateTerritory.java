package com.cc.downunder.stateTerritory;

import com.cc.downunder.datastore.CreateEntity;

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

    public void createEntity(String state, String capital, int population) {
        CreateEntity createEntity = new CreateEntity();
        createEntity.createStateEntity(state, capital, population);

    }
}
