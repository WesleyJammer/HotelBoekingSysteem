package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import com.example.boeking.hotelboekingsysteem.Models.Sidebar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDate;


import static java.lang.System.currentTimeMillis;

public class AddScreen {

    private final Stage stage;
    private final Scene addscene;
    private boolean active;





    public AddScreen(Stage stage, Scene addscene, Database db) {

        this.stage = stage;
        this.addscene = addscene;



        HBox root = new HBox();
        addscene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/addscreen.css").toString());



        Sidebar sb = new Sidebar(stage,db);

        VBox voegToeContainer = new VBox();
        voegToeContainer.setPrefSize(735,600);

        FlowPane voegToeTitleContainer = new FlowPane();
        voegToeTitleContainer.setAlignment(Pos.CENTER);
        voegToeTitleContainer.setPadding(new Insets(20,50,0,0));

        Label voegToeTitle = new Label();
        voegToeTitle.setText("Voeg Toe");
        voegToeTitle.setId("voegToeTitle");



        FlowPane voegToeInputContainer = new FlowPane();
        voegToeInputContainer.setAlignment(Pos.CENTER);
        voegToeInputContainer.setPadding(new Insets(60,50,0,0));
        voegToeInputContainer.setId("voegToeInputContainer");

        GridPane voegToeInput = new GridPane();
        voegToeInput.setVgap(10);


        TextField txtVoorNaam = new TextField();
        txtVoorNaam.setPromptText("Voornaam");
        txtVoorNaam.setId("voegToeInput");

        TextField txtAchterNaam = new TextField();
        txtAchterNaam.setPromptText("Ächternaam");
        txtAchterNaam.setId("voegToeInput");

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        txtEmail.setId("voegToeInput");

        TextField txtTelefoon = new TextField();
        txtTelefoon.setPromptText("Telefoonnummer");
        txtTelefoon.setId("voegToeInput");

        TextField txtAantalPersonen = new TextField();
        txtAantalPersonen.setPromptText("Aantal personen");
        txtAantalPersonen.setId("voegToeInput");

        DatePicker dateAankomst = new DatePicker();
        dateAankomst.setId("voegToeInput");

        Label tot =  new Label();
        tot.setText("T/M");
        tot.setId("tot");

        DatePicker dateVertrek = new DatePicker();
        dateVertrek.setId("voegToeInput");
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

        Button btnOpslaan = new Button("Opslaan");
        btnOpslaan.setId("voegToeInput");

        btnOpslaan.setOnAction(e->{
Connection conn = db.getConn();

            String voorNaam = txtVoorNaam.getText();
            String achterNaam = txtAchterNaam.getText();
            String email = txtEmail.getText();
            int telefoon = Integer.parseInt(txtTelefoon.getText());
            int aantalPersonen = Integer.parseInt(txtAantalPersonen.getText());
            Timestamp aankomst = Timestamp.valueOf(dateAankomst.getValue().atStartOfDay());
            Timestamp vertrek = Timestamp.valueOf(dateVertrek.getValue().atStartOfDay());
            String kamerType = cmbKamerType.getValue().toString();

            if (aankomst != null && vertrek != null) { db.opslaanBoeking(voorNaam,achterNaam,email, telefoon, aantalPersonen, aankomst, vertrek, kamerType);}


            AddScreen a = new AddScreen(stage,new Scene(new HBox(),900,600),db);
            stage.setScene(a.getAddscene());


        });


        voegToeInput.add(txtVoorNaam,0,1);
        voegToeInput.add(txtAchterNaam,0,2);
        voegToeInput.add(txtEmail,0,3);
        voegToeInput.add(txtTelefoon,0,4);
        voegToeInput.add(txtAantalPersonen,0,5);
        voegToeInput.add(dateAankomst,0,6);
        voegToeInput.add(tot,0,7);
        voegToeInput.add(dateVertrek,0,8);
        voegToeInput.add(cmbKamerType,0,9);
        voegToeInput.add(btnOpslaan,0,10);




        voegToeTitleContainer.getChildren().add(voegToeTitle);


        voegToeInputContainer.getChildren().add(voegToeInput);

        voegToeContainer.getChildren().addAll(voegToeTitleContainer,voegToeInputContainer);




        root.getChildren().addAll(sb,voegToeContainer);

        addscene.setRoot(root);
        stage.setScene(addscene);
        stage.setTitle("Voeg toe");
    }

    public Scene getAddscene() {
        return addscene;
    }




}
