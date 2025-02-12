package com.example.bookreview;

import java.sql.*;

public class DBconnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/BookReview";
    private static final String USER = "admin";
    private static final String PASSWORD = "book";

    public static Connection connection(){
        try{
            return DriverManager.getConnection(URL,USER,PASSWORD);
        }catch(SQLException e){
            System.err.println("Connection failed: " + e.getMessage());
            return null;
        }
    }
//    public static ResultSet query(String query){
//        ResultSet res = null;
//        try(Connection con = DBconnection.connection();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(query);){
//            res=rs;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return res;
//    }
}
