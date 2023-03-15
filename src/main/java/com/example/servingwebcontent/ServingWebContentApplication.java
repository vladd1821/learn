package com.example.servingwebcontent;

import com.example.servingwebcontent.internal.UserCreator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class ServingWebContentApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(ServingWebContentApplication.class, args);
    }

}
