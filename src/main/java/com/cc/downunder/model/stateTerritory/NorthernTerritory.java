package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class NorthernTerritory
        extends StateTerritory {

    public NorthernTerritory() {
        super("Darwin", 245600);
        this.setTravelInfo(new TravelInfo(getCapital(), "NT"));
        createEntity("Northern Territory", getCapital(), getPopulation());
    }
}
