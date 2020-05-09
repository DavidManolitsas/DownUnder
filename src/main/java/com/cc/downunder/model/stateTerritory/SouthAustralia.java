package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class SouthAustralia
        extends StateTerritory {

    public SouthAustralia() {
        super("Adelaide", 1756500);
        this.setTravelInfo(new TravelInfo(getCapital()));
        createEntity("South Australia", getCapital(), getPopulation());

    }
}
