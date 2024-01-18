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

public class AddScreen {

    private final Stage stage;
    private final Scene addscene;
    private boolean active;





    public AddScreen(Stage stage, Scene addscene) {

        this.stage = stage;
        this.addscene = addscene;


        HBox root = new HBox();
        addscene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/addscreen.css").toString());

        Sidebar sb = new Sidebar(stage);


        root.getChildren().addAll(sb);

        addscene.setRoot(root);
        stage.setScene(addscene);
        stage.setTitle("Voeg toe");
    }

    public Scene getAddscene() {
        return addscene;
    }




}
