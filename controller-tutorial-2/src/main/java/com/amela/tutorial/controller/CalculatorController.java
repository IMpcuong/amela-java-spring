package com.amela.tutorial.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    private final String ADDITION = "Add";
    private final String SUBTRACT = "Sub";
    private final String MULTIPLY = "Mul";
    private final String DIVIDEND = "Div";

    @GetMapping
    public String showCalculator() {
        return "calculator";
    }

    @PostMapping
    public ModelAndView calculateForm(@RequestParam("option") String options, @RequestParam("operand") String... operands) {
        StringBuilder result = new StringBuilder();
        long op1 = Long.parseLong(operands[0]);
        long op2 = Long.parseLong(operands[1]);

        switch (options)
        {
            case ADDITION:
                result.append(ADDITION + " result: ").append(op1 + op2);
                break;
            case SUBTRACT:
                result.append(SUBTRACT + " result: ").append(op1 - op2);
                break;
            case MULTIPLY:
                result.append(MULTIPLY + " result: ").append(op1 * op2);
                break;
            case DIVIDEND:
                if (op2 == 0)
                    result.append("Error!!! Second number must be greater or lesser than 0");
                else
                    result.append(DIVIDEND + " result: ").append((double) op1 / op2);
                break;
        }

        return new ModelAndView("calculator", "calculator-result", result);
    }
}
