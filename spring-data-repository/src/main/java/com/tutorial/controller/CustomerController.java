package com.tutorial.controller;

import com.tutorial.model.Customer;
import com.tutorial.model.Province;
import com.tutorial.service.customer.ICustomerService;
import com.tutorial.service.province.IProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IProvinceService provinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces(){
        return provinceService.findAll();
    }

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
        modelAndView.addObject("message_create", "New customer created successfully!");

        return modelAndView;
    }

    @GetMapping("/edit-customer/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        var customer = customerService.findById(id);
        ModelAndView modelAndView;
        if (customer.isPresent()) {
            modelAndView = new ModelAndView("/customer/edit");
            modelAndView.addObject("customer", customer.get());

        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/edit-customer")
    public ModelAndView updateCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.save(customer);

        ModelAndView modelAndView = new ModelAndView("/customer/edit");
        modelAndView.addObject("customer", customer);
        modelAndView.addObject("message_update", "Customer updated successfully!");

        return modelAndView;
    }

    @GetMapping("/delete-customer/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Optional<Customer> customer = customerService.findById(id);

        ModelAndView modelAndView;
        if (customer.isPresent()) {
            modelAndView = new ModelAndView("/customer/delete");
            modelAndView.addObject("customer", customer.get());

        } else {
            modelAndView = new ModelAndView("/error-404");
        }
        return modelAndView;
    }

    @PostMapping("/delete-customer")
    public String deleteCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.remove(customer.getId());
        long totalItems = customerService.findAll().spliterator().getExactSizeIfKnown();

        final int pageSize = 10;
        int totalPages = (int) (totalItems / pageSize + 1);
        int page = (int) (customer.getId() / pageSize);
        int currentPage = Math.min(totalPages, page);
        String currentPos = Integer.toString(currentPage);
        //true only with the last page because my index is messy!
        return new StringBuilder().append("redirect:customers/").append(currentPos).append("?sortField=id&sortDir=asc").toString();
    }

    @GetMapping(value = {"/customers/{pageNum}", "/search/{pageNum}"})
    public ModelAndView listCustomersWithPagingAndSorting(
            Pageable pageable,
            @RequestParam(value = "search") Optional<String> input,
            @PathVariable("pageNum") int pageNum,
            @Param("sortField") String sortField,
            @Param("sortDir") String sortDir) {
        Page<Customer> customers;
        ModelAndView modelAndView;

        if (input.isPresent()) {
            customers = customerService.findAllByFirstNameContaining(input.get(), pageable);
            modelAndView = new ModelAndView("/customer/result");

            modelAndView.addObject("inputSearch", input);
            modelAndView.addObject("customers", customers);
        } else {
            customers = customerService.findAllPagingAndSorting(pageNum, sortField, sortDir, pageable);
            modelAndView = new ModelAndView("/customer/list");

            modelAndView.addObject("currentPage", pageNum);
            modelAndView.addObject("totalPages", customers.getTotalPages());
            modelAndView.addObject("totalItems", customers.getTotalElements());
            modelAndView.addObject("customers", customers);

            modelAndView.addObject("sortField", sortField);
            modelAndView.addObject("sortDir", sortDir);
            modelAndView.addObject("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        }

        return modelAndView;
    }

    @GetMapping("/customers/1?sortName=null&sortDir=null")
    public String homePage() {
        return "customer/list";
    }

//    public ModelAndView listCustomers(Pageable pageable) {
//        //Iterable<Customer> customers = customerService.findAllPagingAndSorting(); without paging
//        Page<Customer> customers = customerService.findAllPagingAndSorting(pageable);
//        ModelAndView modelAndView = new ModelAndView("/customer/list");
//        modelAndView.addObject("customers", customers);
//        return modelAndView;
//    }
}
