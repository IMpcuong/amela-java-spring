package TutorialSpringMVC.Controllers;

import TutorialSpringMVC.Models.CustomerTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {


    @GetMapping("/greeting")
    public String greeting(@RequestParam String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @Autowired
    private CustomerTest customerTest;
    @GetMapping("/customers-test")
    public String listCustomer() {
        String name = customerTest.getName();
        return name;
    }
}
