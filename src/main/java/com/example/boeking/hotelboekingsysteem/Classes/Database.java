package com.example.boeking.hotelboekingsysteem.Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    private Connection connection;

    public Database(Connection connection) {
        this.connection = connection;


        String url = "jdbc:mysql://localhost:3306/hotel_boeking_systeem?user=root&password=";

        try {
            connection = DriverManager.getConnection(url);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
