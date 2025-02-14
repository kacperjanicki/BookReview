package com.example.bookreview;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    String name;
    public User(String name){
        this.name = name;
    }
    static boolean userExists(String name){
        String sql = "SELECT * FROM person WHERE Name = ?;";
        try(
                Connection con = DBconnection.connection();
                PreparedStatement stmt = con.prepareStatement(sql);
                ) {
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
//                System.out.println(rs.getString("UserID"));
                return true;
            }else{
//                System.out.println("nouser");
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
