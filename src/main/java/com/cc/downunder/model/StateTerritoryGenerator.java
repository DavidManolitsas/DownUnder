package com.cc.downunder.model;

import com.cc.downunder.model.stateTerritory.AustralianCapitalTerritory;
import com.cc.downunder.model.stateTerritory.NewSouthWales;
import com.cc.downunder.model.stateTerritory.NorthernTerritory;
import com.cc.downunder.model.stateTerritory.Queensland;
import com.cc.downunder.model.stateTerritory.SouthAustralia;
import com.cc.downunder.model.stateTerritory.Tasmania;
import com.cc.downunder.model.stateTerritory.Victoria;
import com.cc.downunder.model.stateTerritory.WesternAustralia;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

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

    private Datastore datastore;
    private KeyFactory keyFactory;

    private static StateTerritoryGenerator instance;

    private StateTerritoryGenerator() {
        datastore = DatastoreOptions.getDefaultInstance().getService();
        keyFactory = datastore.newKeyFactory().setKind("State");
    }

    public static StateTerritoryGenerator getInstance() {
        if (instance == null) {
            instance = new StateTerritoryGenerator();
        }
        return instance;
    }

    public NorthernTerritory getNt() {
        if (nt == null) {
            Key key = keyFactory.newKey("Northern Territory");
            Entity ntEntity = datastore.get(key);
            String capital = ntEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(ntEntity.getProperties().get("population").get().toString());

            nt = new NorthernTerritory(capital, population);
        }
        return nt;
    }

    public Queensland getQld() {
        if (qld == null) {
            Key key = keyFactory.newKey("Queensland");
            Entity qldEntity = datastore.get(key);
            String capital = qldEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(qldEntity.getProperties().get("population").get().toString());

            qld = new Queensland(capital, population);
        }
        return qld;
    }

    public NewSouthWales getNsw() {
        if (nsw == null) {
            Key key = keyFactory.newKey("New South Wales");
            Entity nswEntity = datastore.get(key);
            String capital = nswEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(nswEntity.getProperties().get("population").get().toString());

            nsw = new NewSouthWales(capital, population);
        }
        return nsw;
    }

    public AustralianCapitalTerritory getAct() {
        if (act == null) {
            Key key = keyFactory.newKey("Australian Capital Territory");
            Entity actEntity = datastore.get(key);
            String capital = actEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(actEntity.getProperties().get("population").get().toString());

            act = new AustralianCapitalTerritory(capital, population);
        }
        return act;
    }

    public Victoria getVic() {
        if (vic == null) {
            Key key = keyFactory.newKey("Victoria");
            Entity vicEntity = datastore.get(key);
            String capital = vicEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(vicEntity.getProperties().get("population").get().toString());

            vic = new Victoria(capital, population);
        }
        return vic;
    }

    public Tasmania getTas() {
        if (tas == null) {
            Key key = keyFactory.newKey("Tasmania");
            Entity tasEntity = datastore.get(key);
            String capital = tasEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(tasEntity.getProperties().get("population").get().toString());

            tas = new Tasmania(capital, population);
        }
        return tas;
    }

    public SouthAustralia getSa() {
        if (sa == null) {
            Key key = keyFactory.newKey("South Australia");
            Entity waEntity = datastore.get(key);
            String capital = waEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(waEntity.getProperties().get("population").get().toString());

            sa = new SouthAustralia(capital, population);
        }
        return sa;
    }

    public WesternAustralia getWa() {
        if (wa == null) {
            Key key = keyFactory.newKey("Western Australia");
            Entity waEntity = datastore.get(key);
            String capital = waEntity.getProperties().get("capital").get().toString();
            int population = Integer.parseInt(waEntity.getProperties().get("population").get().toString());

            wa = new WesternAustralia(capital, population);
        }
        return wa;
    }
}
