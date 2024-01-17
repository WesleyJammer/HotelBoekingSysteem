package com.example.boeking.hotelboekingsysteem;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        HBox root = new HBox();
        Scene scene = new Scene(root, 900, 600);
        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/HelloApplication.css").toString());
//        scene.getStylesheets().add(HelloApplication.class.getResource("fonts/Salsa-Regular.ttf").toString());

        Font font = Font.loadFont(HelloApplication.class.getResource("fonts/Salsa-Regular.ttf").toString(), 20);


        VBox sidebar = new VBox();
        sidebar.setPrefSize(165,600);
        sidebar.setId("sidebar");
        sidebar.setSpacing(20);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20,0,0,0));


        FlowPane menuTitle = new FlowPane();
        menuTitle.setAlignment(Pos.CENTER);
        menuTitle.setPadding(new Insets(0,0,0,0));

        Label menuTitleText = new Label();
        menuTitleText.setText("Menu");
        menuTitleText.setId("menu");
        menuTitleText.setAlignment(Pos.TOP_CENTER);
        menuTitleText.setPadding(new Insets(0,0,0,0));


        VBox boekingMenu = new VBox();
        boekingMenu.setSpacing(5);
        boekingMenu.setAlignment(Pos.CENTER);
        boekingMenu.setPrefHeight(150);
        boekingMenu.setId("menuSelect");

        ImageView boekingIcon = new ImageView();
        boekingIcon.setImage(new Image(HelloApplication.class.getResource("icons/boeking.png").toString()));
        boekingIcon.setFitWidth(60);
        boekingIcon.setFitHeight(60);
        boekingIcon.setPreserveRatio(true);


        Label boekingIconText = new Label();
        boekingIconText.setText("Boekingen");
        boekingIconText.setId("menuText");


        VBox wijzigAnnuleerMenu = new VBox();
        wijzigAnnuleerMenu.setSpacing(5);
        wijzigAnnuleerMenu.setAlignment(Pos.CENTER);
        wijzigAnnuleerMenu.setId("menuSelect");
        wijzigAnnuleerMenu.setPrefHeight(150);




        ImageView wijzigAnnuleerIcon = new ImageView();
        wijzigAnnuleerIcon.setImage(new Image(HelloApplication.class.getResource("icons/wijzigAnnuleer.png").toString()));
        wijzigAnnuleerIcon.setFitWidth(60);
        wijzigAnnuleerIcon.setFitHeight(60);
        wijzigAnnuleerIcon.setPreserveRatio(true);

        Label wijzigAnnuleerIconText = new Label();
        wijzigAnnuleerIconText.setText("Wijzig/Annuleer");
        wijzigAnnuleerIconText.setId("menuText");

        VBox voegToeMenu = new VBox();
        voegToeMenu.setSpacing(5);
        voegToeMenu.setAlignment(Pos.CENTER);
        voegToeMenu.setPrefHeight(150);
        voegToeMenu.setId("menuSelect");

        ImageView toevoegenIcon = new ImageView();
        toevoegenIcon.setImage(new Image(HelloApplication.class.getResource("icons/Toevoegen.png").toString()));
        toevoegenIcon.setFitWidth(60);
        toevoegenIcon.setFitHeight(60);
        toevoegenIcon.setPreserveRatio(true);


        Label toevoegenIconText = new Label();
        toevoegenIconText.setText("Voeg Toe");
        toevoegenIconText.setId("menuText");




        FlowPane welcome = new FlowPane();
        welcome.setPrefSize(735,600);
        welcome.setAlignment(Pos.CENTER);
        welcome.setId("welcome");

        Label welcomeText = new Label();
        welcomeText.setText("Welkom");
        welcomeText.setId("welcomeText");
        welcomeText.setPadding(new Insets(0,40,100,0));


         menuTitle.getChildren().add(menuTitleText);
         boekingMenu.getChildren().addAll(boekingIcon,boekingIconText);
         wijzigAnnuleerMenu.getChildren().addAll(wijzigAnnuleerIcon,wijzigAnnuleerIconText);
         voegToeMenu.getChildren().addAll(toevoegenIcon,toevoegenIconText);


        sidebar.getChildren().addAll(menuTitle,boekingMenu,wijzigAnnuleerMenu,voegToeMenu);
        welcome.getChildren().add(welcomeText);

        root.getChildren().addAll(sidebar,welcome);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}