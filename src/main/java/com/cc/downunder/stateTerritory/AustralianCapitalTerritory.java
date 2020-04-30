package com.cc.downunder.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class AustralianCapitalTerritory
        extends StateTerritory {

    public AustralianCapitalTerritory() {
        super("Canberra", 428100);
        createEntity("Australian Capital Territory", getCapital(), getPopulation());
    }


}
