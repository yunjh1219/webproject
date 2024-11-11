package com.home.project_megamenu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "pages/main";
    }

    @GetMapping("/test")
    public String test() {
        return "pages/test";
    }

}
