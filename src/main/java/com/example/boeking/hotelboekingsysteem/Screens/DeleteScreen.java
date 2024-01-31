package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.BoekingInf;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteScreen {

    private final Stage stage;
    private final Scene scene;

    public DeleteScreen(Stage stage, Scene scene, Boeking b) {

        this.stage = stage;
        this.scene = scene;

        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/deletescreen.css").toString());

        FlowPane root = new FlowPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(500);
        root.setVgap(20);


       // Ook voor het delete scherm word de class BoekingInf aangeroepen zodat de gebruiker kan inzien welke boeking hij op het punt staat te verwijderen
        BoekingInf bI = new BoekingInf( b);


        GridPane deleteContainer = new GridPane();
        deleteContainer.setHgap(40);

        Button btnDeleted = new Button("Annuleer");
        btnDeleted.setPrefSize(100, 20);
        btnDeleted.setId("btndeleted");


        // een checkbox aangemaakt die de gebruiker aanmoet vinken om een boeking te kunnen verwijderen
        CheckBox chkAkkoord = new CheckBox("Weet u zeker dat u deze boeking wilt verwijderen");


        Database db = new Database();

        btnDeleted.setOnAction(e -> {


          // een methode in de deleteknop SetOnAction methode is aangemaakt om te controleren of de checkbox wel is ingevuld

            if(chkAkkoord.isSelected()){
          // als de checkbox dus aangevinkt is zal het systeem dus de VerwijderBoeking functie uit mijn database class aanroepen en dus de boeking verwijderen.
                db.verwijderBoeking(b);

           // ook zal hij de gebruiker weer terug sturen naar het keuze scherm waarin de gebruiker kan kiezen tussen het aanpassen van een boeking of het verwijderen van een boeking.
                ModifyDeleteScreen md = new ModifyDeleteScreen(stage, new Scene(new HBox(), 900, 600), db);
                stage.setScene(md.getModifyDeletescene());

            } else {

                // als de checkbox dus niet is aangevinkt komt er een alert bericht op het scherm met daarin het verzoek dat de gebruiker eerst de checkbox heeft aangevinkt.

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Waarschuwing");
                alert.setHeaderText(null);
                alert.setContentText("Bevestig dat u deze boeking wilt verwijderen");

                alert.showAndWait();
                return;

            }




        });


        Label btnTerug = new Label();
        btnTerug.setText("Ga Terug");
        btnTerug.setId("btnterug");
        btnTerug.setPrefSize(200, 40);
        btnTerug.setAlignment(Pos.CENTER);


// eenterug knop die naar het keuzemenu van wijzigen of verwijderen gaat.
        btnTerug.setOnMouseClicked(e -> {

            ModifyDeleteViewScreen mdv = new ModifyDeleteViewScreen(stage, new Scene(new HBox(), 900, 600), b);
            stage.setScene(mdv.getScene());

        });

        deleteContainer.add(btnDeleted, 0, 1);
        deleteContainer.add(chkAkkoord, 1, 1);

        root.getChildren().addAll(bI, deleteContainer, btnTerug);


        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Annuleer");

    }


    public Scene getScene() {
        return scene;
    }
}
