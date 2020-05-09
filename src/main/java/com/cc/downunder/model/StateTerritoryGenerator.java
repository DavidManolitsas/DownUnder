package com.cc.downunder.model;

import com.cc.downunder.model.stateTerritory.AustralianCapitalTerritory;
import com.cc.downunder.model.stateTerritory.NewSouthWales;
import com.cc.downunder.model.stateTerritory.NorthernTerritory;
import com.cc.downunder.model.stateTerritory.Queensland;
import com.cc.downunder.model.stateTerritory.SouthAustralia;
import com.cc.downunder.model.stateTerritory.Tasmania;
import com.cc.downunder.model.stateTerritory.Victoria;
import com.cc.downunder.model.stateTerritory.WesternAustralia;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-05-09
 */
public class StateTerritoryGenerator {

    private NorthernTerritory nt;
    private Queensland qld;
    private NewSouthWales nsw;
    private AustralianCapitalTerritory act;
    private Victoria vic;
    private Tasmania tas;
    private SouthAustralia sa;
    private WesternAustralia wa;

    private static StateTerritoryGenerator instance;

    private StateTerritoryGenerator() {

    }

    public static StateTerritoryGenerator getInstance() {
        if (instance == null) {
            instance = new StateTerritoryGenerator();
        }
        return instance;
    }

    public NorthernTerritory getNt() {
        if (nt == null) {
            nt = new NorthernTerritory();
        }
        return nt;
    }

    public Queensland getQld() {
        if (qld == null) {
            qld = new Queensland();
        }
        return qld;
    }

    public NewSouthWales getNsw() {
        if (nsw == null) {
            nsw = new NewSouthWales();
        }
        return nsw;
    }

    public AustralianCapitalTerritory getAct() {
        if (act == null) {
            act = new AustralianCapitalTerritory();
        }
        return act;
    }

    public Victoria getVic() {
        if (vic == null) {
            vic = new Victoria();
        }
        return vic;
    }

    public Tasmania getTas() {
        if (tas == null) {
            tas = new Tasmania();
        }
        return tas;
    }

    public SouthAustralia getSa() {
        if (sa == null) {
            sa = new SouthAustralia();
        }
        return sa;
    }

    public WesternAustralia getWa() {
        if (wa == null) {
            wa = new WesternAustralia();
        }
        return wa;
    }
}
