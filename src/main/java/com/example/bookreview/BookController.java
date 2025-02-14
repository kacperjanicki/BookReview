package com.example.bookreview;

import javafx.scene.control.Label;

public class BookController {
    @javafx.fxml.FXML
    private Label label;

    public void setTitle(String t){
        label.setText(t);
    }
}
