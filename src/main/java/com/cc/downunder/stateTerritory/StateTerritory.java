package com.cc.downunder.stateTerritory;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
public abstract class StateTerritory {

    private String capital;


    public StateTerritory(String capital) {
        this.capital = capital;
    }


    public String getCapital() {
        return capital;
    }
}
