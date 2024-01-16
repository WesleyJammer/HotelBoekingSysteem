module com.example.boeking.hotelboekingsysteem {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.boeking.hotelboekingsysteem to javafx.fxml;
    exports com.example.boeking.hotelboekingsysteem;
}