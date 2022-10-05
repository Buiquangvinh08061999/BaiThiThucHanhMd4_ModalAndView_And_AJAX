package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")

public class ListController {

    @GetMapping
    public ModelAndView showLoginPage(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/listCity");
        return modelAndView;
    }
}
