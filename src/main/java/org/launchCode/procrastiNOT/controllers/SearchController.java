package org.launchCode.procrastiNOT.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SearchController {
    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns");
        return "search";
    }
}
