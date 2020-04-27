package com.cc.downunder.controller;

import com.cc.downunder.stateTerritory.AustralianCapitalTerritory;
import com.cc.downunder.stateTerritory.NewSouthWales;
import com.cc.downunder.stateTerritory.NorthernTerritory;
import com.cc.downunder.stateTerritory.Queensland;
import com.cc.downunder.stateTerritory.SouthAustralia;
import com.cc.downunder.stateTerritory.Tasmania;
import com.cc.downunder.stateTerritory.Victoria;
import com.cc.downunder.stateTerritory.WesternAustralia;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
@Controller
public class IndexController {

    // States and Territories
    private NorthernTerritory nt = new NorthernTerritory();
    private Queensland qld = new Queensland();
    private NewSouthWales nsw = new NewSouthWales();
    private AustralianCapitalTerritory act = new AustralianCapitalTerritory();
    private Victoria vic = new Victoria();
    private Tasmania tas = new Tasmania();
    private SouthAustralia sa = new SouthAustralia();
    private WesternAustralia wa = new WesternAustralia();


    @GetMapping("/")
    public String getHomePage(Model model) {
        //add each state and territory variable to the index page
        model.addAttribute("northernTerritory", nt);
        model.addAttribute("queensland", qld);
        model.addAttribute("newSouthWales", nsw);
        model.addAttribute("australianCapitalTerritory", act);
        model.addAttribute("victoria", vic);
        model.addAttribute("tasmania", tas);
        model.addAttribute("southAustralia", sa);
        model.addAttribute("westernAustralia", wa);

        return "index";
    }
}