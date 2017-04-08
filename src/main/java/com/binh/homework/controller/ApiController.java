package com.binh.homework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by binh on 2017/4/8.
 */
@Controller("/api")
public class ApiController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login() {

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public void delete() {

    }

    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public void buy() {

    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload() {

    }
}
