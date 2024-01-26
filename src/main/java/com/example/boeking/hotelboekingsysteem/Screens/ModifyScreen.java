package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class ModifyScreen {
private final Stage stage;

private final Scene scene;
    public ModifyScreen(Stage stage, Scene scene, Boeking b) {
        this.stage = stage;
        this.scene = scene;

      VBox root = new VBox();

        GridPane ModifyInput = new GridPane();
        ModifyInput.setVgap(10);

        TextField txtVoorNaam = new TextField();
        txtVoorNaam.setText(b.getVoorNaam());
        txtVoorNaam.setId("modifyinput");

        TextField txtAchterNaam = new TextField();
        txtAchterNaam.setText(b.getAchterNaam());
        txtAchterNaam.setId("modifyinput");

        TextField txtEmail = new TextField();
        txtEmail.setText(b.getEmail());
        txtEmail.setId("modifyinput");

        TextField txtTelefoon = new TextField();
        txtTelefoon.setText(String.valueOf(b.getTelefoon()));
        txtTelefoon.setId("modifyinput");

        TextField txtAantalPersonen = new TextField();
        txtAantalPersonen.setText(String.valueOf(b.getAantalPersonen()));
        txtAantalPersonen.setId("modifyinput");

        DatePicker dateAankomst = new DatePicker();
        dateAankomst.setId("voegToeInput");

        Timestamp t = b.getAankomst();
        LocalDate l = t.toLocalDateTime().toLocalDate();
        dateAankomst.setValue(l);


        Label tot =  new Label();
        tot.setText("T/M");
        tot.setId("tot");

        DatePicker dateVertrek = new DatePicker();
        dateVertrek.setId("voegToeInput");
        Timestamp t2 = b.getVertrek();
        LocalDate l2 = t2.toLocalDateTime().toLocalDate();
        dateVertrek.setValue(l2);

      dateAankomst.setDayCellFactory(picker-> new DateCell(){
        public void updateItem(LocalDate date, boolean empty){

          super.updateItem(date,empty);
          LocalDate today = LocalDate.now();
          setDisable(empty || date.compareTo(today)< 0);

        }

      });

      dateVertrek.setDayCellFactory(picker -> new DateCell(){

        public void updateItem(LocalDate date, boolean empty){
          super.updateItem(date,empty);
          LocalDate minDate = dateAankomst.getValue().plusDays(1);
          setDisable(empty || date.compareTo(minDate)< 0 );

        }
      });

      dateAankomst.valueProperty().addListener((observable, oldvalue, newvalue)->{

        LocalDate minDepartureDate = newvalue.plusDays(1);
        if(dateVertrek.getValue() != null && dateVertrek.getValue().isBefore(minDepartureDate)){

          dateVertrek.setValue(minDepartureDate);
        }

        dateVertrek.setDayCellFactory(picker -> new DateCell(){
          public void updateItem(LocalDate date, boolean empty){

            super.updateItem(date,empty);
            LocalDate minDate = newvalue.plusDays(1);
            setDisable(empty|| date.compareTo(minDate) < 0 );

          }
        });

      });

        ComboBox cmbKamerType = new ComboBox();
        cmbKamerType.getItems().addAll("standaard","familie","deluxe","vip");
        cmbKamerType.setId("voegToeInput");
        cmbKamerType.setValue(b.getKamerType());

        Button btnGewijzigd = new Button("Wijzigen");

      Database db = new Database();

        btnGewijzigd.setOnAction(e->{



          b.setVoorNaam(txtVoorNaam.getText());
          b.setAchterNaam(txtAchterNaam.getText());
          b.setEmail(txtEmail.getText());
          b.setTelefoon(Integer.valueOf(txtTelefoon.getText()));
          b.setAantalPersonen(Integer.valueOf(txtAantalPersonen.getText()));
          Timestamp a = Timestamp.valueOf(dateAankomst.getValue().atStartOfDay());
          Timestamp v = Timestamp.valueOf(dateVertrek.getValue().atStartOfDay());
          b.setAankomst(a);
          b.setVertrek(v);
          b.setKamerType(cmbKamerType.getValue().toString());

          db.wijzigBoeking(b);



        });



        ModifyInput.add(txtVoorNaam,0,1);
        ModifyInput.add(txtAchterNaam,0,2);
        ModifyInput.add(txtEmail,0,3);
        ModifyInput.add(txtTelefoon,0,4);
        ModifyInput.add(txtAantalPersonen,0,5);
        ModifyInput.add(dateAankomst,0,6);
        ModifyInput.add(tot,0,7);
        ModifyInput.add(dateVertrek,0,8);
        ModifyInput.add(cmbKamerType,0,9);
        ModifyInput.add(btnGewijzigd,0,10);



        root.getChildren().addAll(ModifyInput);

        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Wijzig");

    }

    public Scene getScene() {
        return scene;
    }
}
