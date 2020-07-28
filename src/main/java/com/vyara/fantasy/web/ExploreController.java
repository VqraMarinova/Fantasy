package com.vyara.fantasy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ExploreController {

    @GetMapping("/explore/{items}")
    public String getExploreItemsScreen(@PathVariable String items) {
        return "exploreAll";
    }

    @GetMapping("/explore")
    public String getExploreScreen() {
        return "exploreAll";
    }

    @GetMapping("/explore/{item}/{id}")
    public String getExploreItemScreen(@PathVariable String item, @PathVariable Long id) {
        return "exploreElement";
    }
}
