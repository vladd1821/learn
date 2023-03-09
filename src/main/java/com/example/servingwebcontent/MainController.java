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
   // @Autowired
    //private PersonRepository personRepository;
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
        Person person = new Person(name,Integer.parseInt(age));
        userCreator.addUser(person);
       // personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/archieve")
    public String showArchieve(Model model){
       userCreator.showArchieve(model);
      //  Iterable<Person> persons = userCreator.getAllUser();
       // Iterable<Person> persons = personRepository.findAll();
        //model.addAttribute("persons", persons);
        return "archieve";
    }

}
