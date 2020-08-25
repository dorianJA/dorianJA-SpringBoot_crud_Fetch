package ru.jm.springboot2.springboot2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.jm.springboot2.springboot2.Repository.RoleRepository;
import ru.jm.springboot2.springboot2.model.Role;
import ru.jm.springboot2.springboot2.model.User;
import ru.jm.springboot2.springboot2.service.UserService;
import ru.jm.springboot2.springboot2.validator.UserValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
public class MainController {


    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;


    @GetMapping(value = "/")
    public String adminPage(ModelMap model, @AuthenticationPrincipal UserDetails user) {
        List<Role> roles = (List<Role>) roleRepository.findAll();
        User userInfo = userService.getUserByName(user.getUsername());
        model.addAttribute("aboutUser", userInfo);
        model.addAttribute("allRoles", roles);
        return "users";
    }

}
