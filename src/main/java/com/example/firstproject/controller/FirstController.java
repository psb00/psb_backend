package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("hi")
    public String niceToMeetYou(Model model){
        model.addAttribute("username","문재인");
        return "greetings";
    }
    @GetMapping("/bye")
    public String helloWorld(Model model){
        model.addAttribute("username","문재인");
        return "goodbye";
    }

}
