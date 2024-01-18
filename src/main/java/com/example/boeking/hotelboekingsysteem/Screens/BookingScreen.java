package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingScreen {
private final Stage stage;
private final Scene bookingscene;




    public BookingScreen(Stage stage, Scene bookingscene) {

       this.stage = stage;
       this.bookingscene = bookingscene;

        HBox root = new HBox();
        bookingscene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/bookingscreen.css").toString());

     Sidebar sb = new Sidebar(stage);



        root.getChildren().addAll(sb);

   bookingscene.setRoot(root);
   stage.setScene(bookingscene);
   stage.setTitle("Boekingen");
    }


    public Scene getBookingscene() {
        return bookingscene;
    }
}
