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



    public ModifyDeleteScreen(Stage stage, Scene modifyDeletescene, Database db) {
        this.stage = stage;
        this.modifyDeletescene = modifyDeletescene;

        HBox root = new HBox();
        modifyDeletescene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/modifydeletescreen.css").toString());

        Sidebar sb = new Sidebar(stage,db);

        VBox wijzigAnnuleerContainer = new VBox();
        wijzigAnnuleerContainer.setPrefSize(735,600);

        FlowPane wijzigAnnuleerTitleContainer = new FlowPane();
        wijzigAnnuleerTitleContainer.setAlignment(Pos.CENTER);
        wijzigAnnuleerTitleContainer.setPadding(new Insets(20,50,0,0));

        Label wijzigAnnuleerTitle = new Label();
        wijzigAnnuleerTitle.setText("Wijzig/Annuleer");
        wijzigAnnuleerTitle.setId("wijzigAnnuleerTitle");

        FlowPane tableViewContainer = new FlowPane();
        tableViewContainer.setPrefWidth(600);
        tableViewContainer.setAlignment(Pos.BOTTOM_CENTER);
        tableViewContainer.setPadding(new Insets(150,50,0,0));


        TableView tv = new TableView<>();
        tv.setId("tableview");
        tv.setPrefWidth(450);


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

            ModifyDeleteViewScreen mdv = new ModifyDeleteViewScreen(stage, new Scene(new VBox(),900,600),boeking);
            stage.setScene(mdv.getScene());
             });
        tableViewContainer.getChildren().addAll(tv);

        wijzigAnnuleerTitleContainer.getChildren().add(wijzigAnnuleerTitle);


        wijzigAnnuleerContainer.getChildren().addAll(wijzigAnnuleerTitleContainer,tableViewContainer);

        root.getChildren().addAll(sb,wijzigAnnuleerContainer);



        modifyDeletescene.setRoot(root);
        stage.setScene(modifyDeletescene);
        stage.setTitle("Wijzig");

    }

    public Scene getModifyDeletescene() {
        return modifyDeletescene;
    }
}
