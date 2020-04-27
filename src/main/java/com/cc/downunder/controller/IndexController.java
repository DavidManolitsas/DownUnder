package com.cc.downunder.controller;

import com.cc.downunder.states.Victoria;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */

@Controller
public class IndexController {

    private Victoria vic = new Victoria();

    //    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @GetMapping("/")
    public String getHomePage(Model model) {
        model.addAttribute("melbourne", vic.getCapital());
        return "index";
    }
}