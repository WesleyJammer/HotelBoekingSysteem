package com.example.boeking.hotelboekingsysteem.Models;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoekingInf extends VBox {

    private Stage stage;

    private Boeking boeking;
    public BoekingInf(Stage stage, Boeking boeking) {
        this.stage = stage;
        this.boeking = boeking;

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



        boekingContainer.getChildren().addAll(boekingInf1, boekingInf2);
        getChildren().addAll(boekingId, boekingContainer);
    }
}