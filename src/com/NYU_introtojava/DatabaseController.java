package com.NYU_introtojava;

import org.sqlite.JDBC;
import java.sql.*;

public class DatabaseController extends JDBC {

    public static void connectToDatabase(){
        Connection connection = null;
        String URL = "";

        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Database Connection Failed");
        }
    }


}
