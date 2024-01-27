package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.Sidebar;
import com.example.boeking.hotelboekingsysteem.Models.TableViewBoeking;
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


        TableViewBoeking tv = new TableViewBoeking();

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
