package com.example.project01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ApplicationController {
    @Value("${spring.application.name}")
    String appName;


    @GetMapping(value="/")
    public String homePage(Model model){
        model.addAttribute("appName", appName); 
        return "index";
    }

}
