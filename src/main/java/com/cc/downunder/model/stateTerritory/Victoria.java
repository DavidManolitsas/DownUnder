package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-28
 */
public class Victoria
        extends StateTerritory {

    private TravelInfo info;

    public Victoria() {
        super("Melbourne", 6629900);
        createEntity("Victoria", getCapital(), getPopulation());
    }

    public TravelInfo getInfo() {
        return info;
    }
}
