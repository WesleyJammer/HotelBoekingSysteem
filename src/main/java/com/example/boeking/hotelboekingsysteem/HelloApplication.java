package com.example.boeking.hotelboekingsysteem;

import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.Models.Sidebar;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        // basis container aanmaken voor mijn scherm deze heet in de meeste gevallen root. Als type heb ik een Hbox gebruikt waardoor in dit geval de sidebar en het welkom scherm naast elkaar komen te staan
        HBox root = new HBox();

        // De scene van in dit geval het startscherm is het zichtbare oppervlak in de applicatie waarin ook daadwerkelijk content zichtbaar is in tegenstelling tot de stage die ook leeg zichtbaar kan zijn. ik heb hem in dit geval een breedte van 900 gegeveb en een hoogte van 600
        // ook heb ik de root aan de scene toegevoegd.
        Scene scene = new Scene(root, 900, 600);

        //de ccs file die toebehoort aan dit scherm gekoppeld aan dit scherm zodat de styling zoals kleuren toegepast worden.
        scene.getStylesheets().add(getClass().getResource("stylesheets/HelloApplication.css").toString());

        // De de grote van de stage vast gezet. het scherm kan dus niet groter of kleiner gemaakt worden bij gebruik van de applicatie.
        stage.setResizable(false);

        // Een exterte font(lettertype stijl) gekoppeld aan de applicatie. hierna hoef ik hem alleen nog maar aan te roepen in de css files
        Font font = Font.loadFont(getClass().getResource("fonts/Salsa-Regular.ttf").toString(), 20);

       // de class Database aangeroepen zodat hij in dit scherm gebruikt kan worden
        Database db = new Database();

        // De class sidebar aangeroepen zodat ik mijn gemaakte sidebar in dit scherm kan gebruiken.
        // ook de stage en db instanties meegegeven aan de constructor omdat deze in de class sidebar ook standaard meegegeven worden in de constructor als variabele.
        Sidebar sb = new Sidebar(stage, db);

// een flowpane is een container structuur die zoals de naam al zegt zijn items in de richting zet zoals de progameur ze instelt.
        FlowPane welcome = new FlowPane();
        welcome.setPrefSize(735, 600);

        // container in het midden van zijn parent container geplaatst.
        welcome.setAlignment(Pos.CENTER);

        // door een id intestellen voor items of containers kan je in je css files de items of container makkelijk aanroepen om styling erop toe te passen
        welcome.setId("welcome");

// een label aangemaakt met daarop de tekst Welkom
        Label welcomeText = new Label();
        welcomeText.setText("Welkom");
        welcomeText.setId("welcomeText");

        // met padding kan je de items beter verplaatsen binnen de container zodat ze beter vallen binnen het design van het scherm.
        welcomeText.setPadding(new Insets(0, 40, 100, 0));

// met een getchildren method roep je eigenlijk de items binnen een container aan zodat deze items ook daadwerkelijk zichtbaar worden op het scherm.
        welcome.getChildren().add(welcomeText);

        root.getChildren().addAll(sb, welcome);

        stage.setTitle("Hello!");

        // de scene koppelen aan de stage(het daadwerkelijke gebied waarin alles binnen het scherm geplaatst word)
        stage.setScene(scene);

        // zorgen dat de stage ook daadwerkelijk zichtbaar is. de stage is in feite leeg maar wel zichtbaar als er geen scene aangekoppeld is.
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}