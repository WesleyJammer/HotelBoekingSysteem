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



        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/boekingviewscreen.css").toString());


        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(300);
        root.setVgap(20);

        BoekingInf bI = new BoekingInf(stage, boeking);



        Label btnWijzigen = new Label();
        btnWijzigen.setText("Ga naar wijzigen");
        btnWijzigen.setId("btngaterug");
        btnWijzigen.setAlignment(Pos.CENTER);
        btnWijzigen.setPrefSize(200,40);


        btnWijzigen.setOnMouseClicked(e -> {

     ModifyScreen ms = new ModifyScreen(stage, new Scene(new VBox(),900,600),boeking);
     stage.setScene(ms.getScene());



        });

       Label btnAnnuleren = new Label();
        btnAnnuleren.setText("Ga naar Annuleren");
        btnAnnuleren.setId("btngaterug");
        btnAnnuleren.setPrefSize(200,40);



        btnAnnuleren.setOnMouseClicked(e -> {
            DeleteScreen ds = new DeleteScreen(stage, new Scene(new VBox(),900,600),boeking);
           stage.setScene(ds.getScene());



       });



        root.getChildren().addAll(bI,btnWijzigen,btnAnnuleren);


        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Wijzig/Annuleer View");

    }

    public Scene getScene() {
        return scene;
    }
}
