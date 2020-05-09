package com.cc.downunder.model.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class NorthernTerritory
        extends StateTerritory {

    public NorthernTerritory() {
        super("Darwin", 245600);
        createEntity("Northern Territory", getCapital(), getPopulation());
    }
}
