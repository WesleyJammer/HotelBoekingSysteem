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
    public void start(Stage stage ) throws IOException {

        HBox root = new HBox();
        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/helloapplication.css").toString());


        stage.setResizable(false);

        Font font = Font.loadFont(HelloApplication.class.getResource("fonts/Salsa-Regular.ttf").toString(), 20);


        Database db = new Database();
        Sidebar sb = new Sidebar(stage,db);



        FlowPane welcome = new FlowPane();
        welcome.setPrefSize(735,600);
        welcome.setAlignment(Pos.CENTER);
        welcome.setId("welcome");

        Label welcomeText = new Label();
        welcomeText.setText("Welkom");
        welcomeText.setId("welcomeText");
        welcomeText.setPadding(new Insets(0,40,100,0));





        welcome.getChildren().add(welcomeText);

        root.getChildren().addAll(sb,welcome);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}