package com.example.bookreview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.awt.print.Book;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookReviewController {

    @FXML
    private Button logout;
    @FXML
    private Label user;
    @FXML
    private Button profile;

    private BookReviewApplication app;
    public void setApplication(BookReviewApplication app){this.app = app;}


    @FXML
    void initialize(){
        user.setText(AuthManager.getLoggedInUser());
    }

    @FXML
    void logout(){
        try{
            AuthManager.clearLoginState();
            app.changeScene("welcome.fxml");
        }catch (IOException e){e.printStackTrace();}
    }

//    public void onDBconnectClick(ActionEvent actionEvent) {
//        try(Connection con = DBconnection.connect();
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT name FROM author;")){
//            if(rs.next()){
//                connectLabel.setText("Connected "+rs.getString(1));
//            }
//        } catch (SQLException e) {
//            connectLabel.setText("Connection error");
//            e.printStackTrace();
//        }
//    }
}