package com.cc.downunder.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class WesternAustralia
        extends StateTerritory {

    public WesternAustralia() {
        super("Perth", 2630600);
        createEntity("Western Australia", getCapital(), getPopulation());
    }
}
