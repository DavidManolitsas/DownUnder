package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;
import com.cc.downunder.model.datastore.CreateEntity;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public abstract class StateTerritory {

    private String capital;
    private int population;
    private TravelInfo travelInfo;


    public StateTerritory(String capital, int population) {
        this.capital = capital;
        this.population = population;

    }


    public void createEntity(String state, String capital, int population) {
        CreateEntity createEntity = new CreateEntity();
        createEntity.createStateEntity(state, capital, population);

    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public TravelInfo getTravelInfo() {
        return travelInfo;
    }

    public void setTravelInfo(TravelInfo travelInfo) {
        this.travelInfo = travelInfo;
    }
}
