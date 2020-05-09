package com.cc.downunder.model.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-28
 */
public class Victoria
        extends StateTerritory {

    public Victoria() {
        super("Melbourne", 6629900);
        createEntity("Victoria", getCapital(), getPopulation());
    }
}
