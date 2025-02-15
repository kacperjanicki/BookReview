package com.example.bookreview;

import javafx.scene.control.Button;

public class BookPageController {
    private BookReviewApplication app;
    @javafx.fxml.FXML
    private Button test;

//    public void setApplication(BookReviewApplication app){this.app = app;}

    public void test(){
        System.out.println("test");
    }

    public void setApplication(BookReviewApplication app) {
        this.app = app;
    }
}
