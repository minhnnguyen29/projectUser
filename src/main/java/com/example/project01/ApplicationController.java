package com.example.project01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ApplicationController {
    @Value("${spring.application.name}")
    String appName; 

    @GetMapping(path="/")
    public String homePage(Model model){
        model.addAttribute("appName", appName); 
        return "home";
    }
    
}
