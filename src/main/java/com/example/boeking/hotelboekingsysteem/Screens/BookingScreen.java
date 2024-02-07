package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingScreen  {
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



        FlowPane tableViewContainer = new FlowPane();
        tableViewContainer.setPrefWidth(600);
        tableViewContainer.setAlignment(Pos.BOTTOM_CENTER);
        tableViewContainer.setPadding(new Insets(150,50,0,0));


// hier roep ik de class TableView aan zodat ik een tabellen scherm in me scene kan zetten waarin ik mijn ingevoerde boekingen die in de database staan kan laten zien.
        TableView tv = new TableView<>();
        tv.setId("tableview");

        // breedte van het tabellen scherm instellen
        tv.setPrefWidth(540);

// kolommen van het tabellen scherm instellen op basis van het aanroepen van De class Boeking waarin de variabelen staan van een beoking en de juiste datatype.
        // ook geef ik een naam voor het desbtreffende kolom
        TableColumn col0 = new TableColumn<Boeking, Integer>("Boeking ID");
        // de juiste variabele uit mijn Boeking class koppelen aan de juiste kolom
        col0.setCellValueFactory(new PropertyValueFactory<>("boekingId"));

        TableColumn col1 = new TableColumn<Boeking, String>(" Voornaam");
        col1.setCellValueFactory(new PropertyValueFactory<>("voorNaam"));

        TableColumn col2 = new TableColumn<Boeking, String>(" Achternaam");
        col2.setCellValueFactory(new PropertyValueFactory<>("achterNaam"));

        TableColumn col3 = new TableColumn<Boeking, String>(" Email");
        col3.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn col4 = new TableColumn<Boeking, String>(" Telefoon nummer");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefoon"));

// alle kolommen ook daadwerkelijk toevoegen aan de tableview
        tv.getColumns().addAll(col0,col1,col2,col3,col4);
        //de geefalleboekingen functie uit mijn database class toevoegen aan mijn TablieView zodat de boekingen ook daadwerkelijk worden weergeven
        tv.getItems().addAll(db.geefAlleBoekingen());


        // omdat dit geen button is kan je geen setOnAction method gebruiken, maar wel een setOnMOuseCLicked wat eigen lijk dezelfde functie heeft.
        tv.setOnMouseClicked(e->{

            // in het geval van de table view kan je een willekeurige boeking aanklikken en die zal je naar de BoekingViewScreen verwijzen waarop de zoujuist geselecteerde boeking word weergeven doormiddel van met aanroepen van de boeking class.
            Boeking boeking = (Boeking) tv.getSelectionModel().getSelectedItem();

            BookingViewScreen bvs = new BookingViewScreen(stage, boeking,new Scene(new HBox(),900,600));
            stage.setScene(bvs.getScene());
        });


        boekingTitleContainer.getChildren().add(boekingTitle);

        tableViewContainer.getChildren().add(tv);

        boekingContainer.getChildren().addAll(boekingTitleContainer,tableViewContainer);

        root.getChildren().addAll(sb,boekingContainer);

   bookingscene.setRoot(root);
   stage.setScene(bookingscene);
   stage.setTitle("Boekingen");
    }


    public Scene getBookingscene() {
        return bookingscene;
    }
}
