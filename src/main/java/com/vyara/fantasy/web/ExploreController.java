package com.vyara.fantasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExploreController {

    @GetMapping("/explore")
    public String getAddBookForm() {
        return "explore";
    }



}
