package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-28
 */
public class Victoria
        extends StateTerritory {

    public Victoria() {
        super("Melbourne", 6629900);
        this.setTravelInfo(new TravelInfo(getCapital()));
        createEntity("Victoria", getCapital(), getPopulation());
    }

}
