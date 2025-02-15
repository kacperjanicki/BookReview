package com.example.bookreview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.util.Objects;

public class BookReviewApplication extends Application {
    private Stage primaryStage;
    private Scene loginScene,mainScene;

    @Override
    public void start(Stage stage) throws IOException {
        this.primaryStage = stage;

        FXMLLoader mainLoader = new FXMLLoader(BookReviewApplication.class.getResource("main.fxml"));
        Parent startRoot = mainLoader.load();

        FXMLLoader welcomeLoader = new FXMLLoader(BookReviewApplication.class.getResource("welcome.fxml"));
        Parent welcomeRoot = welcomeLoader.load();
        WelcomeController welcomeController = welcomeLoader.getController();
        welcomeController.setApplication(this);
//
//        FXMLLoader bookpageLoader = new FXMLLoader(BookReviewApplication.class.getResource("bookPage.fxml"));
//        Parent bookpageRoot = bookpageLoader.load();
//        BookController bkController = bookpageLoader.getController();
//        bkController.setApplication(this);

        Scene main = new Scene(startRoot, 600, 400);
//        Scene login = new Scene(loginRoot, 500, 300);
        Scene welcome = new Scene(welcomeRoot, 600, 400);
//        Scene bookView = new Scene(bookpageRoot,600,400);

        stage.setTitle("BookReview");
        stage.setResizable(false);
        stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("library-placeholder.png"))));


        stage.setScene(welcome);
        stage.setResizable(false);
        stage.show();
    }

    public void changeScene(String fmxlFile) throws IOException{
        FXMLLoader loader = new FXMLLoader(BookReviewApplication.class.getResource(fmxlFile));
        Parent root = loader.load();
        Object controller = loader.getController();

        if(controller instanceof BookReviewController){
            ((BookReviewController) controller).setApplication(this);
        }else if (controller instanceof RegisterController) {
            ((RegisterController) controller).setApplication(this);
        } else if (controller instanceof BookController) {
            ((BookController) controller).setApplication(this);
        } else if (controller instanceof BookPageController) {
            ((BookPageController) controller).setApplication(this);
            ((BookPageController) controller).test(); // Pass book data
        }
//        else if(controller instanceof BookPageController){
//            ((BookPageController) controller).setApplication(this);
//        }
        primaryStage.setScene(new Scene(root,600,400));
    }

    public static void main(String[] args) {
        Connection con = DBconnection.connection();
        if(con != null) System.out.println("connected to db successfully");
        launch();
    }
}