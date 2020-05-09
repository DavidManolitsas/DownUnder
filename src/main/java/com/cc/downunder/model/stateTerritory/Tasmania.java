package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class Tasmania
        extends StateTerritory {

    public Tasmania() {
        super("Hobart", 535500);
        this.setTravelInfo(new TravelInfo(getCapital()));
        createEntity("Tasmania", getCapital(), getPopulation());
    }
}
