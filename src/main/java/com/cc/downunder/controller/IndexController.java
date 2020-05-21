package com.cc.downunder.controller;

import com.cc.downunder.model.LanguageFilter;
import com.cc.downunder.model.Month;
import com.cc.downunder.model.StateTerritoryGenerator;
import com.cc.downunder.model.Year;
import com.cc.downunder.model.gcp.translate.Language;
import com.cc.downunder.model.gcp.vision.DetectLandmark;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */
@Controller
public class IndexController {

    // States and Territories
    private StateTerritoryGenerator generator = StateTerritoryGenerator.getInstance();
    private LanguageFilter languageFilter = LanguageFilter.getInstance();
    private DetectLandmark detectLandmark = new DetectLandmark();
    private Year year = new Year();

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getHomePage(Model model) {
        //pass data to from the model to the view
        //Northern Territory
        model.addAttribute("northernTerritory", generator.getNt());
        model.addAttribute("ntTravelInfo", generator.getNt().getTravelInfo());
        //Queensland
        model.addAttribute("queensland", generator.getQld());
        model.addAttribute("qldTravelInfo", generator.getQld().getTravelInfo());
        //New South Wales
        model.addAttribute("newSouthWales", generator.getNsw());
        model.addAttribute("nswTravelInfo", generator.getNsw().getTravelInfo());
        //Australian Capital Territory
        model.addAttribute("australianCapitalTerritory", generator.getAct());
        model.addAttribute("actTravelInfo", generator.getAct().getTravelInfo());
        //Victoria
        model.addAttribute("victoria", generator.getVic());
        model.addAttribute("vicTravelInfo", generator.getVic().getTravelInfo());
        //Tasmania
        model.addAttribute("tasmania", generator.getTas());
        model.addAttribute("tasTravelInfo", generator.getTas().getTravelInfo());
        //South Australia
        model.addAttribute("southAustralia", generator.getSa());
        model.addAttribute("saTravelInfo", generator.getSa().getTravelInfo());
        //Western Australia
        model.addAttribute("westernAustralia", generator.getWa());
        model.addAttribute("waTravelInfo", generator.getWa().getTravelInfo());

        model.addAttribute("languageFilter", languageFilter);
        //add the month enum
        model.addAttribute("months", Month.values());
        //add language enum
        model.addAttribute("languages", Language.values());

        model.addAttribute("detectLandmark", detectLandmark);

        model.addAttribute("years", year.getYearList());


        //template name not the file name (i.e no .html)
        return "index";
    }

    @RequestMapping(value = "", params = "ntYear", method = RequestMethod.POST)
    public String setYear(@RequestParam String travelYear, Model model){
        generator.getNt().getTravelInfo().setTravelYear(travelYear);
//        generator.getNt().getTravelInfo().getStateYearlyVisitors();
        return "redirect:#NT";
    }

    @RequestMapping(value = "", params = "lang", method = RequestMethod.POST)
    public String setLanguage(@RequestParam Language language) {
        languageFilter.setLanguage(language);
        return "redirect:";
    }

    @RequestMapping(value = "", params = "image", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("imageFile") MultipartFile file){
        detectLandmark.identifyLandmark(file);
        return "redirect:";
    }

    @RequestMapping(value = "", params = "nt", method = RequestMethod.POST)
    public String displayNtTravelInfo(@RequestParam Month travelMonth) {
        generator.getNt().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#NT";
    }

    @RequestMapping(value = "", params = "qld", method = RequestMethod.POST)
    public String displayQldTravelInfo(@RequestParam Month travelMonth) {
        generator.getQld().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#QLD";
    }

    @RequestMapping(value = "", params = "nsw", method = RequestMethod.POST)
    public String displayNswTravelInfo(@RequestParam Month travelMonth) {
        generator.getNsw().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#NSW";
    }

    @RequestMapping(value = "", params = "act", method = RequestMethod.POST)
    public String displayActTravelInfo(@RequestParam Month travelMonth) {
        generator.getAct().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#ACT";
    }

    @RequestMapping(value = "", params = "vic", method = RequestMethod.POST)
    public String displayVicTravelInfo(@RequestParam Month travelMonth) {
        generator.getVic().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#VIC";
    }

    @RequestMapping(value = "", params = "tas", method = RequestMethod.POST)
    public String displayTasTravelInfo(@RequestParam Month travelMonth) {
        generator.getTas().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#TAS";
    }

    @RequestMapping(value = "", params = "sa", method = RequestMethod.POST)
    public String displaySaTravelInfo(@RequestParam Month travelMonth) {
        generator.getSa().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#SA";
    }

    @RequestMapping(value = "", params = "wa", method = RequestMethod.POST)
    public String displayWaTravelInfo(@RequestParam Month travelMonth) {
        generator.getWa().getTravelInfo().setTravelMonth(travelMonth);
        return "redirect:#WA";
    }


}