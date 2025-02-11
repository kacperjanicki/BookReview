module com.example.bookreview {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bookreview to javafx.fxml;
    exports com.example.bookreview;
}