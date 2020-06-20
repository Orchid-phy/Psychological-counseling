package com.orchid.counselling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/self")
public class SelfController {

    @GetMapping("/index")
    public String selfIndex(){

        return "self/index";
    }

    @GetMapping("/posttask")
    public String posttask(){

        return "self/posttask";
    }

    @GetMapping("/userRecord")
    public String userRecord(){

        return "self/userRecord";
    }

}
