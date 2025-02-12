module com.example.bookreview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires java.prefs;
    requires jbcrypt;


    opens com.example.bookreview to javafx.fxml;
    exports com.example.bookreview;
}