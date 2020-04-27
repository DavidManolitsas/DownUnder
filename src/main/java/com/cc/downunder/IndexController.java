package com.cc.downunder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author David Manolitsas
 * @project downunder
 * @date 2020-04-27
 */


@Controller
public class IndexController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getHomePage() {
        return "index";
    }
}