package com.example.servingwebcontent;

import com.example.servingwebcontent.internal.UserCreator;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;

@Controller
public class MainController {

    @Autowired
    private UserCreator userCreator;

    @GetMapping("/")
    public String homePage(Model model){
        return "home";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model){
        return "registration";
    }

    @PostMapping("/registration")
    public String registrationSubmit(@RequestParam String name, @RequestParam String age, Model model){
        userCreator.addUser(name,age);
        return "redirect:/";
    }

    @GetMapping("/archieve")
    public String showArchieve(Model model){
       userCreator.getAllPersons(model);
        return "archieve";
    }

}
