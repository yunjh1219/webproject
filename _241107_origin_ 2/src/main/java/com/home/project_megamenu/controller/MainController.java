package com.home.project_megamenu.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {



    @GetMapping("/")
    public String index() {
        return "pages/main";
    }

    @GetMapping("/main2")
    public String index2() {
        return "pages/main2";
    }

    @GetMapping("/test")
    public String test() {
        return "pages/test";
    }

    @GetMapping("/rest")
    public String rest() {
        return "pages/reservations";
    }

    @GetMapping("/kakao")
    public String kakako() {
        return "pages/kakao";
    }




}
