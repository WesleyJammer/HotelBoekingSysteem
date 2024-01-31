package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.BoekingInf;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class BookingViewScreen {

    private final Stage stage;
    private final Scene scene;

    public BookingViewScreen(Stage stage, Boeking boeking, Scene scene) {

        this.stage = stage;
        this.scene = scene;


        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/bookingviewscreen.css").toString());


        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(300);
        root.setVgap(20);


// De class BoekingInf aangeroepen waarin een model staat waarop de gegevens van een boeking die uit een tableview word geselecteerd weergeven wordt.
        // door het aanroepen van dit model kan het model dus op dit scherm weergeven worden en hoeft het model niet hier uitgetypt te worden.
        // dit zorgt voor minder code in je schermen, wat handig kan zijn als je een model meerdere keren wilt gebruiken in je applicatie.
        BoekingInf bI = new BoekingInf( boeking);



// label aangemaakt voor een terug gaan knop
        Label btnGaTerug = new Label();
        btnGaTerug.setText("Ga Terug");
        btnGaTerug.setId("btngaterug");
        btnGaTerug.setAlignment(Pos.CENTER);
        btnGaTerug.setPrefSize(200,40);
        btnGaTerug.setId("btngaterug");

        // de terug gaanknopt stuurt de gebruiker weer terug naar de boeking pagina waar de tableview opgesteld staat.
        //de gebruiker zou er dan voor kunne kiezen om een andere boeking aan te klikken en dan word de gebruiker weer terug gestuurd naar deze pagina alleen dan met de gegevens van een andere boeking op het scherm.
        btnGaTerug.setOnMouseClicked(e -> {

            Database db = new Database();


        BookingScreen bs = new BookingScreen(stage, new Scene(new HBox(),900,600),db);
        stage.setScene(bs.getBookingscene());

        });





        root.getChildren().addAll(bI,btnGaTerug);


        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Boeking view");
    }

    public Scene getScene() {
        return scene;
    }
}
