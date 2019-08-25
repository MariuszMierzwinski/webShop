package com.example.webshop.controllers;

import com.example.webshop.models.User;
import com.example.webshop.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;

@Controller
public class UserListController {
    private UserService userService;

    @Autowired
    public UserListController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String getListUser(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        model.addAttribute("lista", userService.findAll());
        model.addAttribute("user", new User());
        // User user=null;
        System.out.println("QQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQQ");


        return "th_list";
    }

    @PostMapping("/list")
    public String addUser(@ModelAttribute User user) {
        System.out.println("PostPostPostPostPostPostPostPostPost");

        userService.addUser(user);

        return "redirect:list";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id, ServletRequest request) throws NotFoundException {
        userService.deleteUser(userService.findById(Long.parseLong(id)));
        return "redirect:/list";
    }

    @PostMapping("/edit/{id}")
    public String edit(Model model,@PathVariable String id, ServletRequest request) throws NotFoundException {
        User user = userService.findById(Long.parseLong(id));
        model.addAttribute("user_to_edit", user);
        return "th_edit";
    }

    @PostMapping("/editable")
    public String edit(@ModelAttribute User user_to_edit)  {
        userService.editUser(user_to_edit);
        return "redirect:/list";
    }


}
