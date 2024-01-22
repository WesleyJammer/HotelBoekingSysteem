package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingScreen {
private final Stage stage;
private final Scene bookingscene;




    public BookingScreen(Stage stage, Scene bookingscene, Database db) {

       this.stage = stage;
       this.bookingscene = bookingscene;

        HBox root = new HBox();
        bookingscene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/bookingscreen.css").toString());


     Sidebar sb = new Sidebar(stage,db);

        VBox boekingContainer = new VBox();
        boekingContainer.setPrefSize(735,600);

        FlowPane boekingTitleContainer = new FlowPane();
        boekingTitleContainer.setAlignment(Pos.CENTER);
        boekingTitleContainer.setPadding(new Insets(20,50,0,0));

        Label boekingTitle = new Label();
        boekingTitle.setText("Boekingen");
        boekingTitle.setId("boekingTitle");

        TableView tv = new TableView<>();

        TableColumn col0 = new TableColumn<>("Boeking ID");
        col0.setCellValueFactory(new PropertyValueFactory<>("boeking_id"));

        TableColumn col1 = new TableColumn<>(" Voornaam");
        col1.setCellValueFactory(new PropertyValueFactory<>("voor_naam"));

        TableColumn col2 = new TableColumn<>(" Achternaam");
        col1.setCellValueFactory(new PropertyValueFactory<>("achter_naam"));

        TableColumn col3 = new TableColumn<>(" Email");
        col1.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn col4 = new TableColumn<>(" Telefoon nummer");
        col1.setCellValueFactory(new PropertyValueFactory<>("telefoon_nummer"));

        tv.getColumns().addAll(col0,col1,col2,col3,col4);

        boekingTitleContainer.getChildren().add(boekingTitle);

        boekingContainer.getChildren().addAll(boekingTitleContainer,tv);

        root.getChildren().addAll(sb,boekingContainer);

   bookingscene.setRoot(root);
   stage.setScene(bookingscene);
   stage.setTitle("Boekingen");
    }


    public Scene getBookingscene() {
        return bookingscene;
    }
}
