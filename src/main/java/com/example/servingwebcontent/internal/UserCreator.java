package com.example.servingwebcontent.internal;

import com.example.servingwebcontent.Person;
import com.example.servingwebcontent.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserCreator {

    @Autowired
    PersonRepository personRepository;

    public void addUser(Person person){
        personRepository.save(person);
    }

    public void deleteUser(Person person){
        personRepository.delete(person);
    }

    public Iterable<Person> getAllUser(){
        return personRepository.findAll();
    }

    public void showArchieve(Model model) {
        Iterable<Person> persons = getAllUser();
        model.addAttribute("persons", persons);
    }
}
