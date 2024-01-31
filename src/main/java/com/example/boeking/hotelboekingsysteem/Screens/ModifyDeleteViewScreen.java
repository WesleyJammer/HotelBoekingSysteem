package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.BoekingInf;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ModifyDeleteViewScreen {
    private final Stage stage;
    private final Scene scene;
    public ModifyDeleteViewScreen(Stage stage, Scene scene, Boeking boeking) {

        this.stage = stage;
        this.scene = scene;



        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/modifydeleteviewscreen.css").toString());


        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(300);
        root.setVgap(20);


// de BoekingInf class weer aanroepen om een boeking te kunnen weergeven op het beeldscherm
        BoekingInf bI = new BoekingInf( boeking);



        Label btnWijzigen = new Label();
        btnWijzigen.setText("Ga naar wijzigen");
        btnWijzigen.setId("btnwijzig-annuleer");
        btnWijzigen.setAlignment(Pos.CENTER);
        btnWijzigen.setPrefSize(200,40);


        // deze knop verwijst naar het scherm om een boeking te wijzigen
        btnWijzigen.setOnMouseClicked(e -> {

     ModifyScreen ms = new ModifyScreen(stage, new Scene(new VBox(),900,600),boeking);
     stage.setScene(ms.getScene());



        });

        Label btnAnnuleren = new Label();
        btnAnnuleren.setText("Ga naar Annuleren");
        btnAnnuleren.setId("btnwijzig-annuleer");
        btnAnnuleren.setAlignment(Pos.CENTER);
        btnAnnuleren.setPrefSize(200,40);



        // deze knop verwijst naar het scherm om een boeking te kunnen annuleren
        btnAnnuleren.setOnMouseClicked(e -> {
            DeleteScreen ds = new DeleteScreen(stage, new Scene(new VBox(),900,600),boeking);
           stage.setScene(ds.getScene());



       });

        Label btnTerug = new Label();
        btnTerug.setText("Ga Terug");
        btnTerug.setId("btnwijzig-annuleer");
        btnTerug.setPrefSize(200,40);
        btnTerug.setAlignment(Pos.CENTER);

        // en met deze knop kan je weer terug gaan naar de TableView uit het wijzigenAnnuleer scherm
        btnTerug.setOnMouseClicked(e->{

            Database db = new Database();

            ModifyDeleteScreen md = new ModifyDeleteScreen(stage,new Scene(new HBox(),900,600),db);
            stage.setScene(md.getModifyDeletescene());

        });



        root.getChildren().addAll(bI,btnWijzigen,btnAnnuleren,btnTerug);


        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Wijzig/Annuleer View");

    }

    public Scene getScene() {
        return scene;
    }
}
