package com.binh.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NeedLoginController {
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String account(ModelMap map) {
        return "account";
    }

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public String publicProduct(ModelMap map) {
        return "public";
    }

    @RequestMapping(value = "/publicSubmit", method = RequestMethod.POST)
    public String publicSubmit(ModelMap map) {
        return "publicSubmit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(ModelMap map) {
        return "edit";
    }

    @RequestMapping(value = "/editSubmit", method = RequestMethod.POST)
    public String editSubmit(ModelMap map) {
        return "editSubmit";
    }
}
