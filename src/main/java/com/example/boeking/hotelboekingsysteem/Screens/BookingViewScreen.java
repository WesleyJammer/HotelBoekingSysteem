package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
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

        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/boekingviewscreen.css").toString());


        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(300);
        root.setVgap(20);


        Label boekingId = new Label();
        boekingId.setText("Boeking ID: " + " " + boeking.getBoekingId());
        boekingId.setId("boekingid");


        HBox boekingContainer = new HBox();
        boekingContainer.setAlignment(Pos.CENTER);
        boekingContainer.setSpacing(30);
        boekingContainer.setPadding(new Insets(40, 40, 40, 40));
        boekingContainer.setId("boekingcontainer");


        VBox boekingInf1 = new VBox();
        boekingInf1.setSpacing(30);

        VBox boekingInf2 = new VBox();
        boekingInf2.setSpacing(30);


        Label voorNaam = new Label();
        voorNaam.setText("Voornaam:" + " " + boeking.getVoorNaam());
        voorNaam.setId("boeking");

        Label achterNaam = new Label();
        achterNaam.setText("Achternaam:" + " " + boeking.getAchterNaam());
        achterNaam.setId("boeking");

        Label email = new Label();
        email.setText("Email:" + " " + boeking.getEmail());
        email.setId("boeking");

        Label telefoonNummer = new Label();
        telefoonNummer.setText("Telefoonnummer:" + " " + boeking.getTelefoon());
        telefoonNummer.setId("boeking");

        Label aantalPersonen = new Label();
        aantalPersonen.setText("Aantal personen:" + " " + boeking.getAantalPersonen());
        aantalPersonen.setId("boeking");

        Label aankomst = new Label();
        aankomst.setText("Aankomst:" + " " + boeking.getAankomst());
        aankomst.setId("boeking");

        Label vertrek = new Label();
        vertrek.setText("Vertrek:" + " " + boeking.getVertrek());
        vertrek.setId("boeking");

        Label kamerType = new Label();
        kamerType.setText("Kamertype:" + " " + boeking.getKamerType());
        kamerType.setId("boeking");


        boekingInf1.getChildren().addAll(
                voorNaam,
                achterNaam,
                email,
                telefoonNummer);

        boekingInf2.getChildren().addAll(
                aantalPersonen,
                aankomst,
                vertrek,
                kamerType);

        Label btnGaTerug = new Label();
        btnGaTerug.setText("Ga Terug");
        btnGaTerug.setId("btngaterug");
        btnGaTerug.setAlignment(Pos.CENTER);
        btnGaTerug.setPrefSize(200,40);
        btnGaTerug.setId("btngaterug");

        btnGaTerug.setOnMouseClicked(e -> {

            Database db = new Database();

        BookingScreen bs = new BookingScreen(stage, new Scene(new HBox(),900,600),db);
        stage.setScene(bs.getBookingscene());

        });


        boekingContainer.getChildren().addAll(boekingInf1, boekingInf2);

        root.getChildren().addAll(boekingId, boekingContainer,btnGaTerug);


        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Boeking view");
    }

    public Scene getScene() {
        return scene;
    }
}
