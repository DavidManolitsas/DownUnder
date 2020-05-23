package com.cc.downunder.model.stateTerritory;

import com.cc.downunder.model.TravelInfo;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-28
 */
public class Queensland
        extends StateTerritory {

    public Queensland(String capital, int population) {
        super(capital, population);
        this.setTravelInfo(new TravelInfo(getCapital(), "QLD"));
    }
}
