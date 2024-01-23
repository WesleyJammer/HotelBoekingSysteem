package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BoekingViewScreen  {

    private final Stage stage;
    private final Scene scene;

    public BoekingViewScreen(Stage stage,Boeking boeking, Scene scene) {

        this.stage = stage;
        this.scene = scene;

        VBox root = new VBox();




        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Boeking view");
    }

    public Scene getScene() {
        return scene;
    }
}
