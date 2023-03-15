package com.example.servingwebcontent.internal;

import com.example.servingwebcontent.Person;
import com.example.servingwebcontent.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserCreator {
    private static Set<Long> allGeneratedId = new HashSet<>();
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

            preparedStatement.setLong(1, generateId());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getName());

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
       // personRepository.save(person);
    }

    public Set<Long> getAllId() throws SQLException {

        PreparedStatement ps = connection.prepareStatement("select id FROM person");
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
          allGeneratedId.add(rs.getLong(1));
        }
        return  allGeneratedId;
    }

    public Long generateId() throws SQLException {
        Long id = Double.valueOf(Math.random()*100000).longValue();
        if (getAllId().contains(id)){
            generateId();
        }
        return id;
    }
}
