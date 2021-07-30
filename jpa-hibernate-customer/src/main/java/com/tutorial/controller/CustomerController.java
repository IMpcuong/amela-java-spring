package com.tutorial.controller;

import com.tutorial.model.Customer;
import com.tutorial.service.ICustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    //Create new customer using JPA
    @GetMapping("/create-customer")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create-customer")
    public ModelAndView saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "New customer was created successfully!");
        return modelAndView;
    }

    //Create customer using Store Procedure
    @GetMapping("/insert-customer")
    public ModelAndView showInsertForm() {
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/insert-customer")
    public ModelAndView saveInsertCustomerProcedure(@ModelAttribute("customer") Customer customer) {
        customerService.insertWithStoredProcedure(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/create");
        modelAndView.addObject("customer", new Customer());
        modelAndView.addObject("message", "Insert new customer by using procedure successfully!");
        return modelAndView;
    }

    //List all customers
    @GetMapping("/customers")
    public ModelAndView listCustomers() {
        List<Customer> customers = customerService.findAll();
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView;
        if (customer != null) {
            modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer);
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView editCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message", "Customer update successfully!");
        return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable long id) {
        Customer customer = customerService.findById(id);
        ModelAndView modelAndView;
        if (customer != null) {
            modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer);
        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        return "redirect:customers";
    }

    @PostMapping("/search")
    public ModelAndView findByName(@RequestParam("nameSearch") String nameSearch) {
        List<Customer> customerSearch = customerService.findByName(nameSearch);
        ModelAndView modelAndView = new ModelAndView("/customer/list");
        if (customerSearch != null) {
            modelAndView.addObject("customers", customerSearch);
            modelAndView.addObject("message", "Result list:");
        } else {
            modelAndView.addObject("message", "Customer not found!");
        }
        return modelAndView;
    }
}
