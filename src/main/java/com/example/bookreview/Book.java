package com.example.bookreview;

import javafx.scene.layout.HBox;

public class Book {
    int id, authorid;
    String title;
    HBox container;
    public Book(int id, int authorid, String title){
        this.id = id;
        this.authorid = authorid;
        this.title = title;
//        this.container = container;
    }
    void info(){
        System.out.println("bookid: "+this.id+" authorid: "+authorid+" title"+title);
    }

}
