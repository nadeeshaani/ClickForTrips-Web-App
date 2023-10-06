package com.nadeeshani.clickForTrips_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ClickForTripsUIController {
    @RequestMapping("/")
    @ResponseBody
    String text(){
        return "Hello";
    }

    @GetMapping("/index")
    String index(){
        return "index";
    }

}
