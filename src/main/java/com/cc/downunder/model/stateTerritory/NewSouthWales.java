package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class NewSouthWales
        extends StateTerritory {

    public NewSouthWales() {
        super("Sydney", 8118000);
        this.setTravelInfo(new TravelInfo(getCapital(), "NSW"));
        createEntity("New South Wales", getCapital(), getPopulation());
    }
}
