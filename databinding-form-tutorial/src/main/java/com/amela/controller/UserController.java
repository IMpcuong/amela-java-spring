package com.amela.controller;

import com.amela.dao.UserDAO;
import com.amela.model.Login;
import com.amela.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping("/home")
    public ModelAndView home() {
        return new ModelAndView("user/home", "login", new Login());
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("login") Login login){
        User user = userDAO.checkLogin(login);
        if (user == null){
            return new ModelAndView("user/error");
        } else {
            ModelAndView modelAndView = new ModelAndView("user/info");
            modelAndView.addObject("user", user);
            return modelAndView;
        }
    }
}