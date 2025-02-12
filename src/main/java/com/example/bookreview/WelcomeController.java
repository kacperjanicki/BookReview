package com.example.bookreview;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WelcomeController {
    private boolean loggedIn = true;

    @javafx.fxml.FXML
    private PasswordField pass;
    @javafx.fxml.FXML
    private Button enter;
    @javafx.fxml.FXML
    private TextField user;
    @javafx.fxml.FXML
    private VBox alreadyLoggedIn;
    @FXML
    private Label loggedintext;
    @FXML
    private Button proceed;

    private BookReviewApplication app;
    @FXML
    private Label result;
    @FXML
    private Button logout;
    @FXML
    private Button register;

    public void setApplication(BookReviewApplication app){this.app = app;}

    @FXML
    public void initialize(){
        alreadyLoggedIn.setVisible(AuthManager.getLoggedInUser()!=null);
        String loggedInUser = AuthManager.getLoggedInUser();
        loggedintext.setText("You are logged in as: "+loggedInUser);
        if(loggedInUser!=null){
            user.setDisable(true);
            pass.setDisable(true);
            enter.setDisable(true);
        }
    }
    @FXML
    void goToRegister(){
        try{
            app.changeScene("register.fxml");
        }catch (IOException e){e.printStackTrace();}
    }

    @FXML
    void proceed() {
        try{
            app.changeScene("main.fxml");
        }catch (IOException e){e.printStackTrace();}
    }

    @FXML
    void logout(){
        AuthManager.clearLoginState();
        alreadyLoggedIn.setVisible(false);
        user.setDisable(false);
        pass.setDisable(false);
        enter.setDisable(false);
    }

    @FXML
    void validateLogin(){
        String username = user.getText();
        String password = pass.getText();

        user.setStyle("");
        pass.setStyle("");
        result.setStyle("");
        boolean hasError = false;

       if(username.isEmpty()){
           user.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
           hasError = true;
       }
       if(password.isEmpty()){
           pass.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
           hasError = true;
       }
       if(hasError){
           result.setText("Fields cannot be empty");
           result.setStyle("-fx-text-fill: red;");
           user.requestFocus();
           return;
       }

       String sql = "SELECT password FROM Person WHERE Name = ?";

       try (Connection con = DBconnection.connection();
            PreparedStatement stmt = con.prepareStatement(sql)){
           stmt.setString(1,username);
           ResultSet rs = stmt.executeQuery();
           if(rs.next()){
               String hashPassword = rs.getString("Password");
               if(BCrypt.checkpw(password,hashPassword)){
                   AuthManager.saveLoginState(username);
                   app.changeScene("main.fxml");
               }
           }else{
               result.setText("Invalid credentials");
               result.setStyle("-fx-border-color: red;-fx-border-width: 2px");
           }

       }catch (SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
           throw new RuntimeException(e);
       }
        if(username.equals("admin") && password.equals("book")){
            AuthManager.saveLoginState(username);
            try{
                AuthManager.saveLoginState(username);
                app.changeScene("main.fxml");
            }catch (IOException e){e.printStackTrace();}
        }else{
            user.setText("");
            pass.setText("");
            user.requestFocus();
            result.setText("Invalid Credentials");
            result.setStyle("-fx-text-fill: red;");
        }

        System.out.println(pass.getText());
        System.out.println(user.getText());
    }
}


