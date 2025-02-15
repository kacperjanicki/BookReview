package com.example.bookreview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class BookReviewController {

    @FXML
    private Button logout;
    @FXML
    private Label user;

    private BookReviewApplication app;
    @FXML
    private HBox booksContainer;

    public void setApplication(BookReviewApplication app){this.app = app;}

    public ArrayList<Book> getBooks(){
        ArrayList<Book> books = new ArrayList<>();
        try(
                Connection con = DBconnection.connection();
                PreparedStatement stmt = con.prepareStatement("SELECT * FROM book;");
                ResultSet rs = stmt.executeQuery();
                ){
            while(rs.next()){
                int authorID = rs.getInt("authorid");
                int bookID = rs.getInt("authorid");
                String title = rs.getString("title");
                Book bk = new Book(bookID,authorID,title);
                books.add(bk);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return books;
    }
//  Dynamically add VBoxes for books fetched from the DB
    public void loadBooks(){
        booksContainer.getChildren().clear();
        ArrayList<Book> books = getBooks();
        for(Book bk: books){
            try{
                FXMLLoader bookloader = new FXMLLoader(getClass().getResource("book.fxml"));
                VBox bookBox = bookloader.load();
                BookController bkController = bookloader.getController();
                User author = User.getUser(
                        "SELECT Name FROM person WHERE userid = ?;", bk.authorid
                );
                bkController.setData(bk.title,author.name,bk);
                booksContainer.getChildren().add(bookBox);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void initialize(){
        user.setText(AuthManager.getLoggedInUser());
        VBox book1 = (VBox) booksContainer.getChildren().toArray()[0];
//        System.out.println(book1.getId());
        ArrayList<Book> books = getBooks();
        for(Book bk: books) bk.info();
        loadBooks();
    }

    @FXML
    void logout(){
        try{
            AuthManager.clearLoginState();
            app.changeScene("welcome.fxml");
        }catch (IOException e){e.printStackTrace();}
    }

}