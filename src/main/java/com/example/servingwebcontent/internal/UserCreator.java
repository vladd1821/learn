package com.example.servingwebcontent.internal;

import com.example.servingwebcontent.Person;
import com.example.servingwebcontent.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.swing.*;
import java.sql.*;

@Service
public class UserCreator {

    private static final String URL ="jdbc:mysql://localhost:8889/testdb";
    private static final String User ="root";
    private static final String Password = "root";
    private static Connection connection;
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(URL, User, Password);
            System.out.println("! ! ! ! ! CONNECTION SUCSESS! ! ! ! !");
        } catch (SQLException e) {
            System.out.println("NOT SUCSESS");
            throw new RuntimeException(e);

        }
    }

    @Autowired
    PersonRepository personRepository;

    public void deleteUserById(Person person){
        personRepository.deleteById(person.getId());
    }


    public void getAllPersons (Model model) {
        Iterable<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);
    }

    public void addUser(String name, String age) {
        Person person = new Person(name,Integer.parseInt(age));
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into person values(?,?,?)");
            //preparedStatement.setLong(1, person.generateId());
            preparedStatement.setInt(1, person.getAge());
            preparedStatement.setString(2, person.getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
       // personRepository.save(person);
    }
}
