package com.example.boeking.hotelboekingsysteem.Models;

import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Screens.AddScreen;
import com.example.boeking.hotelboekingsysteem.Screens.BookingScreen;
import com.example.boeking.hotelboekingsysteem.Screens.ModifyDeleteScreen;
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

public class Sidebar extends VBox{

    private Stage stage;
    private Database db;



    public Sidebar (Stage stage, Database db) {

        this.stage = stage;
        this.db = db;

        VBox sidebar = new VBox();
        sidebar.setPrefSize(165,600);
        sidebar.setId("sidebar");
        sidebar.setSpacing(20);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20,0,0,0));


        FlowPane menuTitle = new FlowPane();
        menuTitle.setAlignment(Pos.CENTER);
        menuTitle.setPadding(new Insets(0,0,0,0));
        menuTitle.setId("menuTitle");

        Label menuTitleText = new Label();
        menuTitleText.setText("Menu");
        menuTitleText.setId("menuTitleText");
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

        boekingMenu.setOnMouseClicked(e->{


            BookingScreen bs = new BookingScreen(stage, new Scene(new HBox(), 900, 600),db);
            stage.setScene(bs.getBookingscene());


        });


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

        wijzigAnnuleerMenu.setOnMouseClicked(e->{

                    ModifyDeleteScreen md = new ModifyDeleteScreen(stage,new Scene(new HBox(),900,600),db);
                    stage.setScene(md.getModifyDeletescene());
                }
        );

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

        voegToeMenu.setOnMouseClicked( e->{

                    AddScreen as = new AddScreen(stage,new Scene(new HBox(),900,600),db);
                    stage.setScene(as.getAddscene());

                }





        );







        menuTitle.getChildren().add(menuTitleText);
        boekingMenu.getChildren().addAll(boekingIcon,boekingIconText);
        wijzigAnnuleerMenu.getChildren().addAll(wijzigAnnuleerIcon,wijzigAnnuleerIconText);
        voegToeMenu.getChildren().addAll(toevoegenIcon,toevoegenIconText);


        sidebar.getChildren().addAll(menuTitle,boekingMenu,wijzigAnnuleerMenu,voegToeMenu);
        getChildren().add(sidebar);

    }









}
