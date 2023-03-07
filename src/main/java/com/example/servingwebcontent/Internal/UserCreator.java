package com.example.servingwebcontent.Internal;

import com.example.servingwebcontent.Person;
import com.example.servingwebcontent.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCreator {

    @Autowired
    private static PersonRepository personRepository;

    public static void addUser(Person person){
        personRepository.save(person);
    }

    public static void deleteUser(Person person){
        personRepository.delete(person);
    }

    public static Iterable<Person> getAllUsers(){
        Iterable<Person> persons = personRepository.findAll();
        return persons;
    }

}
