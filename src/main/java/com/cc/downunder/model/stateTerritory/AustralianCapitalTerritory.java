package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class AustralianCapitalTerritory
        extends StateTerritory {

    public AustralianCapitalTerritory() {
        super("Canberra", 428100);
        this.setTravelInfo(new TravelInfo(getCapital()));
        createEntity("Australian Capital Territory", getCapital(), getPopulation());
    }


}
