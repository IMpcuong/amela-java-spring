package TutorialSpringMVC.Controllers;

import TutorialSpringMVC.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import TutorialSpringMVC.Models.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CustomerController {

    //    private CustomerService customerService = CustomerServiceFactory.getInstance();
    private final ICustomerService ICustomerService;

    public CustomerController(ICustomerService ICustomerService) {
        this.ICustomerService = ICustomerService;
    }

    @ModelAttribute("customerController")
    public ModelAndView modelAndView() {
        return new ModelAndView();
    }

//    @GetMapping("/customers/view")
//    public ModelAndView showInfo(Model model) {
//        ModelAndView modelAndView = new ModelAndView("info");
//        return modelAndView;
//    }

    @GetMapping("/customers")
    public ModelAndView showList(Model model) {
        ModelAndView modelAndView = new ModelAndView("list");
        List<Customer> customers = ICustomerService.findAll();
        model.addAttribute("customers", customers);
        return modelAndView;
    }

    @PostMapping("/customers")
    public ModelAndView deleteCustomer(@ModelAttribute Model model, @RequestParam Long id) {
        ModelAndView modelAndView = new ModelAndView("list");
        ICustomerService.delete(id);
        List<Customer> customers = ICustomerService.findAll();
        model.addAttribute("customers", customers);
        return modelAndView;
    }
}
