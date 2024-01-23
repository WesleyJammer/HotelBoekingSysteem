module com.example.boeking.hotelboekingsysteem {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;


    opens com.example.boeking.hotelboekingsysteem to javafx.fxml;

    exports com.example.boeking.hotelboekingsysteem;
    opens com.example.boeking.hotelboekingsysteem.Classes to javafx.base;
}