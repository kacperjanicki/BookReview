package com.example.bookreview;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class BookController {

    @javafx.fxml.FXML
    private Label author;
    @javafx.fxml.FXML
    private Label title;
//    private Book book = null;
    private BookReviewApplication app;
    public void setApplication(BookReviewApplication app){this.ap = app;}


    public void setData(String ti, String auth,Book bk){
        title.setText(ti);
        author.setText(auth);
//        book = bk;
    }
    public void onContainerClick(){
        System.out.println("ctn clicked");
        try{
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("bookPage.fxml"));
//            Parent bookPageRoot = loader.load();
//
//            BookPageController bookPageController = loader.getController();
//            bookPageController.test();
            app.changeScene("bookPage.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
