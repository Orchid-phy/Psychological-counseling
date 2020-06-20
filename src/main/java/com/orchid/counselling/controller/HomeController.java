package com.orchid.counselling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(){
        return "home/home";
    }

    @GetMapping("/welcome")
    public String wel(){
        return "home";
    }

    @GetMapping("/home/about")
    public String about(){
        return "home/about";
    }

    @GetMapping("/home/counselling")
    public String counselling(){
        return "home/counselling";
    }

    @GetMapping("/home/blog")
    public String blog(){
        return "home/blog";
    }

    @GetMapping("/home/contact")
    public String contact(){
        return "home/contact";
    }

    @GetMapping("/home/gallery")
    public String gallery(){
        return "home/gallery";
    }

    @GetMapping("/home/service")
    public String service(){
        return "home/service";
    }

    @GetMapping("/home/single")
    public String single(){
        return "home/single";
    }

    @GetMapping("/test2")
    public String test2(){
        return "test2";
    }



}
