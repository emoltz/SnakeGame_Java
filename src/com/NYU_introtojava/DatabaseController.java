package com.NYU_introtojava;

import org.sqlite.JDBC;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DatabaseController extends JDBC {
    Connection connection = null;
    ResultSet resultSet = null;

    public void connectToDatabase(){
        String URL = "jdbc:sqlite:identifier.sqlite";

        try {
            this.connection = DriverManager.getConnection(URL);
            System.out.println("Database connection successful");
        } catch (SQLException e) {
            System.out.println("Database Connection Failed");
        }
    }

    public void addNewRecord(String name, int highScore){
        Statement statement = null;

        String query = "INSERT INTO main.highScores VALUES(?, ?)";
        try{
            assert connection != null;
            statement = connection.createStatement();
        }catch (SQLException e){
            System.out.println("Statement error");
        }


        try {
            assert statement != null;
//            statement.executeUpdate(query);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, highScore);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Execution error");
            e.printStackTrace();
        }


    }


}
