package com.cc.downunder.controller;

import com.cc.downunder.model.Months;
import com.cc.downunder.model.StateTerritoryGenerator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        //add each state and territory variable to the index page
        model.addAttribute("northernTerritory", generator.getNt());
        model.addAttribute("queensland", generator.getQld());
        model.addAttribute("newSouthWales", generator.getNsw());
        model.addAttribute("australianCapitalTerritory", generator.getAct());
        model.addAttribute("victoria", generator.getVic());
        model.addAttribute("tasmania", generator.getTas());
        model.addAttribute("southAustralia", generator.getSa());
        model.addAttribute("westernAustralia", generator.getWa());
        model.addAttribute("months", Months.values());

        //template name not the file name (i.e no .html)
        return "index";
    }

}