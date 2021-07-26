package com.amela.tutorial.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/condiments")
public class CondimentController {

    @GetMapping
    public String showOption() {
        return "sandwich";
    }

    @PostMapping
    public ModelAndView saveForm(@RequestParam("condiment") String... condiment) {
        String sandwichesOption;
        StringBuilder result = new StringBuilder();

        if (condiment.length == 0) {
            sandwichesOption = "You haven't choose yet!";
        } else {
            for (String c : condiment) {
                result.append(c).append(" ");
            }
            sandwichesOption = "You have picked " + "[" + result + "]";
        }

        return new ModelAndView("sandwich", "condiment-option", sandwichesOption);
    }
}
