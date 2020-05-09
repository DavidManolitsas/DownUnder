package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class WesternAustralia
        extends StateTerritory {

    public WesternAustralia() {
        super("Perth", 2630600);
        this.setTravelInfo(new TravelInfo(getCapital()));
        createEntity("Western Australia", getCapital(), getPopulation());
    }
}
