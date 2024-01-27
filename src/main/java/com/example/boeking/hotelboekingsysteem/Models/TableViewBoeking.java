package com.example.boeking.hotelboekingsysteem.Models;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.Screens.BookingViewScreen;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TableViewBoeking extends VBox {


    public TableViewBoeking() {

        Stage stage = new Stage();
        Database db = new Database();

        TableView tv = new TableView<>();
        tv.setId("tableview");
        tv.setPrefWidth(465);


        TableColumn col0 = new TableColumn<Boeking, Integer>("Boeking ID");
        col0.setCellValueFactory(new PropertyValueFactory<>("boekingId"));

        TableColumn col1 = new TableColumn<Boeking, String>(" Voornaam");
        col1.setCellValueFactory(new PropertyValueFactory<>("voorNaam"));

        TableColumn col2 = new TableColumn<Boeking, String>(" Achternaam");
        col2.setCellValueFactory(new PropertyValueFactory<>("achterNaam"));

        TableColumn col3 = new TableColumn<Boeking, String>(" Email");
        col3.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn col4 = new TableColumn<Boeking, String>(" Telefoon nummer");
        col4.setCellValueFactory(new PropertyValueFactory<>("telefoon"));

        tv.getColumns().addAll(col0,col1,col2,col3,col4);
        tv.getItems().addAll(db.geefAlleBoekingen());

        tv.setOnMouseClicked(e->{
            Boeking boeking = (Boeking) tv.getSelectionModel().getSelectedItem();

            BookingViewScreen bvs = new BookingViewScreen(stage, boeking,new Scene(new HBox(),900,600));
            stage.setScene(bvs.getScene());
        });

        getChildren().add(tv);


    }
}
