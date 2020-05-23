package com.cc.downunder.model.gcp.datastore;

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
 * @date 2020-05-23
 * <p>
 * This class creates datastore entities for each state/territory
 */
public class CreateEntities {

    public static void main(String[] args) {
        AustralianCapitalTerritory act = new AustralianCapitalTerritory("Canberra", 428100);
        act.createEntity("Australian Capital Territory", act.getCapital(), act.getPopulation());

        NewSouthWales nsw = new NewSouthWales("Sydney", 8118000);
        nsw.createEntity("New South Wales", nsw.getCapital(), nsw.getPopulation());

        NorthernTerritory nt = new NorthernTerritory("Darwin", 245600);
        nt.createEntity("Northern Territory", nt.getCapital(), nt.getPopulation());

        Queensland qld = new Queensland("Brisbane", 5115500);
        qld.createEntity("Queensland", qld.getCapital(), qld.getPopulation());

        SouthAustralia sa = new SouthAustralia("Adelaide", 1756500);
        sa.createEntity("South Australia", sa.getCapital(), sa.getPopulation());

        Tasmania tas = new Tasmania("Hobart", 535500);
        tas.createEntity("Tasmania", tas.getCapital(), tas.getPopulation());

        Victoria vic = new Victoria("Melbourne", 6629900);
        vic.createEntity("Victoria", vic.getCapital(), vic.getPopulation());

        WesternAustralia wa = new WesternAustralia("Perth", 2630600);
        wa.createEntity("Western Australia", wa.getCapital(), wa.getPopulation());
    }
}
