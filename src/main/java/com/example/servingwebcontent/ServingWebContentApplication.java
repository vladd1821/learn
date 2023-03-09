package com.example.servingwebcontent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args)  {
        SpringApplication.run(ServingWebContentApplication.class, args);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://${MYSQL_HOST:localhost}:8889/testdb","root","root");
             Statement st = conn.createStatement();) {
            Person person = new Person("name", 1);
            PreparedStatement preparedStatement = conn.prepareStatement("select * from person where id = ? and name = ? and age = ?");
            preparedStatement.setLong(1, person.getId());
            preparedStatement.setString(2,person.getName());
            preparedStatement.setInt(3,person.getAge());
            ResultSet resultSet = preparedStatement.executeQuery();

            st.execute("select 1");
        } catch (SQLException e) {
            //обработка ошибки

            //можно делать дополнительную проверку через
            String sqlState = e.getSQLState();
            //sqlState.equals("...")
        }
    }

}
