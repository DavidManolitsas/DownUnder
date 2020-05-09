package com.cc.downunder.model.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public class NewSouthWales
        extends StateTerritory {

    public NewSouthWales() {
        super("Sydney", 8118000);
        createEntity("New South Wales", getCapital(), getPopulation());
    }
}
