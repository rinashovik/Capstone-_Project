package org.launchCode.procrastiNOT.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("search")
public class SearchController {
    /*@RequestMapping("")
    public String search(Model model) {
        List<String> columns = Arrays.asList("Column1", "Column2", "Column3");
        model.addAttribute("columns", columns);

        model.addAttribute("pageTitle", "Search Page");
        return "search";
    }*/
    @RequestMapping("")
    public String search(Model model) {
        model.addAttribute("columns");
        return "search";
    }
}
