package com.example.servingwebcontent;

import com.example.servingwebcontent.Internal.UserCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private static PersonRepository personRepository;



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
        Person person = new Person(name,Integer.parseInt(age));
        UserCreator.addUser(person);
        return "redirect:/";
    }

    @GetMapping("/archieve")
    public String showArchieve(Model model){
        model.addAttribute("persons", UserCreator.getAllUsers());
        return "archieve";
    }

}
