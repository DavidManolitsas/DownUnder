package com.cc.downunder.controller;

import com.cc.downunder.model.Month;
import com.cc.downunder.model.StateTerritoryGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
@Controller
public class IndexController {

    // States and Territories
    private StateTerritoryGenerator generator = StateTerritoryGenerator.getInstance();


    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        //pass data to from the model to the view
        model.addAttribute("northernTerritory", generator.getNt());
        model.addAttribute("queensland", generator.getQld());
        model.addAttribute("newSouthWales", generator.getNsw());
        model.addAttribute("australianCapitalTerritory", generator.getAct());

        model.addAttribute("victoria", generator.getVic());
        model.addAttribute("vicTravelInfo", generator.getVic().getTravelInfo());

        model.addAttribute("tasmania", generator.getTas());
        model.addAttribute("southAustralia", generator.getSa());
        model.addAttribute("westernAustralia", generator.getWa());

        //add the month enum
        model.addAttribute("months", Month.values());


        //template name not the file name (i.e no .html)
        return "index";
    }


    @RequestMapping(value = "", params = "vic", method = RequestMethod.POST)
    public String displayVicTravelInfo(@RequestParam Month travelMonth) {
        generator.getVic().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#VIC";
    }
}