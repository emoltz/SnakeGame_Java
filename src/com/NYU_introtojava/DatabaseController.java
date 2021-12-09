package com.NYU_introtojava;

import org.sqlite.JDBC;
import java.sql.*;

public class DatabaseController extends JDBC {

    public static void connectToDatabase(){
        Connection connection = null;
        String URL = "jdbc:sqlite:identifier.sqlite";

        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Database connection successful");
        } catch (SQLException e) {
            System.out.println("Database Connection Failed");
        }
    }


}
