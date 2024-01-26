package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteScreen {

private final Stage stage;
private final Scene scene;
    public DeleteScreen(Stage stage, Scene scene, Boeking b) {

        this.stage = stage;
        this.scene = scene;

        VBox root = new VBox();


        Button btnDeleted = new Button("Annuleer");

        Database db = new Database();

        btnDeleted.setOnAction(e->{

         db.verwijderBoeking(b);



             ModifyDeleteScreen md = new ModifyDeleteScreen(stage, new Scene(new HBox(),900,600),db);
            stage.setScene(md.getModifyDeletescene());


        });


        root.getChildren().addAll(btnDeleted);


        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Annuleer");

    }


    public Scene getScene() {
        return scene;
    }
}
