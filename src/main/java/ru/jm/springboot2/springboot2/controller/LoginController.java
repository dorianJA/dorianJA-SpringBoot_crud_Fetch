package ru.jm.springboot2.springboot2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class LoginController {


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage(@RequestParam(name = "error", required = false) Boolean error, Model model) {
        if (Boolean.TRUE.equals(error)) {
            model.addAttribute("error", "Incorrect  name or password");
        }
        return "login";
    }
//
//    @RequestMapping(value = "accessDenied", method = RequestMethod.GET)
//    public String accessDeniedPage(){
//        return "accessDenied";
//    }
}
