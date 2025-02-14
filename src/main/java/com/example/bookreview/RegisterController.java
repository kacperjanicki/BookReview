package com.example.bookreview;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.*;
import org.mindrot.jbcrypt.BCrypt;
import java.text.Format;

public class RegisterController {
    @javafx.fxml.FXML
    private DatePicker date;
    @javafx.fxml.FXML
    private Button confirm;
    @javafx.fxml.FXML
    private Button goback;
    @javafx.fxml.FXML
    private RadioButton author;
    @javafx.fxml.FXML
    private TextField fullname;
    @javafx.fxml.FXML
    private RadioButton regular;

    private BookReviewApplication app;
    @FXML
    private Label invalid;
    @FXML
    private PasswordField passwordField;

    public void setApplication(BookReviewApplication app){this.app = app;}

    @FXML
    void initialize(){
        fullname.setText("");
        passwordField.setText("");
        author.setSelected(false);
        regular.setSelected(false);
        date.setValue(null);

    }
    @FXML
    void submit() throws SQLException {
        String name = fullname.getText().trim();
        String pass = passwordField.getText().trim();
        String hashedPassword = BCrypt.hashpw(pass.trim(),BCrypt.gensalt(12));
        String dateBirth = String.valueOf(date.getValue());
        String role = regular.isSelected() ? "User" : "Author";

        fullname.setStyle("");
        passwordField.setStyle("");
        date.setStyle("");
        invalid.setText("");
        regular.setStyle("");
        author.setStyle("");

        boolean hasError = false;

        if(name.equals("")){
            fullname.setStyle("-fx-border-color: red;-fx-border-width: 2px");
            invalid.setText("Name field empty");
            hasError = true;
        }
        if (pass.isEmpty()) {
            passwordField.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            invalid.setText("Password field is empty");
            hasError = true;
        }
        if (date.getValue() == null) {
            date.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            invalid.setText("Date of Birth is required");
            hasError = true;
        }
        if(!regular.isSelected() && !author.isSelected()){
            regular.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            author.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            invalid.setText("You need to select if u want to sign up as user or author\ntest");
            hasError=true;
        }

        if (hasError) {
            fullname.requestFocus();
            date.setValue(null);
            return; // Stop execution if any field is invalid
        }



        String sql = "INSERT INTO Person(Name,Role,DateOfBirth,Password) VALUES (?,?,DATE (?),?);";
        try(Connection con = DBconnection.connection();
            PreparedStatement stmt = con.prepareStatement(sql);
            ){

            if(User.userExists(name)){
                invalid.setText("User already exists");
                invalid.setStyle("-fx-text-fill: red");
                return;
            }
            stmt.setString(1,name);
            stmt.setString(2,role);
            stmt.setString(3,dateBirth);
            stmt.setString(4,hashedPassword);

            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected>0){
                System.out.println("User added successfuly");
                try{
                    app.changeScene("main.fxml");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{
                System.out.println("Failed to add the user");
                invalid.setText("Failed to add the user");
                invalid.setStyle("-fx-text-fill: red");
            }



        }
        catch (SQLException e) {
            if (e.getSQLState().equals("23505")) { // PostgreSQL unique violation error code
                invalid.setText("Username already exists! Choose another.");
            } else {
                invalid.setText("Database error: " + e.getMessage());
            }
            invalid.setStyle("-fx-text-fill: red;");
            e.printStackTrace(); // Keep this to debug other errors
        }
        }

    @FXML
    void preventTwoRadio(){
        regular.setDisable(author.isSelected());
        author.setDisable(regular.isSelected());
    }
    @FXML
    void clear(){
        fullname.setText("");
        passwordField.setText("");
        author.setSelected(false);
        author.setDisable(false);
        regular.setSelected(false);
        regular.setDisable(false);
        date.setValue(null);
    }

    @FXML
    void goBack(){
        clear();
        try{
            app.changeScene("welcome.fxml");
        }catch (IOException e){e.printStackTrace();}
    }
}
