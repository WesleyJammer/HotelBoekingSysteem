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

import java.util.PrimitiveIterator;

public class ModifyDeleteScreen {

private final Stage stage;
private final Scene modifyDeletescene;



    public ModifyDeleteScreen(Stage stage, Scene modifyDeletescene) {
        this.stage = stage;
        this.modifyDeletescene = modifyDeletescene;

        HBox root = new HBox();
        modifyDeletescene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/modifydeletescreen.css").toString());

        Sidebar sb = new Sidebar(stage);



        root.getChildren().addAll(sb);

        modifyDeletescene.setRoot(root);
        stage.setScene(modifyDeletescene);
        stage.setTitle("Wijzig/Annuleer");

    }

    public Scene getModifyDeletescene() {
        return modifyDeletescene;
    }
}
