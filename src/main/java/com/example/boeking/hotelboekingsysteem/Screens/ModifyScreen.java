package com.example.boeking.hotelboekingsysteem.Screens;

import com.example.boeking.hotelboekingsysteem.Classes.Boeking;
import com.example.boeking.hotelboekingsysteem.Classes.Database;
import com.example.boeking.hotelboekingsysteem.HelloApplication;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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

        scene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/modifyscreen.css").toString());

        VBox root = new VBox();

        FlowPane bookingTitleContainer = new FlowPane();
        bookingTitleContainer.setAlignment(Pos.CENTER);
        bookingTitleContainer.setPadding(new Insets(20, 50, 0, 0));

        Label inputTitle = new Label();
        inputTitle.setText("Boeking ID:" + " " + b.getBoekingId());
        inputTitle.setId("bookingtitle");

        VBox modifyInputContainer = new VBox();
        modifyInputContainer.setAlignment(Pos.CENTER);
        modifyInputContainer.setPadding(new Insets(0, 50, 0, 0));
        modifyInputContainer.setSpacing(20);
        modifyInputContainer.setId("modifyInputContainer");

        GridPane modifyInput = new GridPane();
        modifyInput.setAlignment(Pos.CENTER);
        modifyInput.setVgap(10);

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
        dateAankomst.setId("modifyinput");

        Timestamp t = b.getAankomst();
        LocalDate l = t.toLocalDateTime().toLocalDate();
        dateAankomst.setValue(l);


        Label tot = new Label();
        tot.setText("T/M");
        tot.setId("tot");

        DatePicker dateVertrek = new DatePicker();
        dateVertrek.setId("modifyinput");
        Timestamp t2 = b.getVertrek();
        LocalDate l2 = t2.toLocalDateTime().toLocalDate();
        dateVertrek.setValue(l2);

        dateAankomst.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {

                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today) < 0);

            }

        });

        dateVertrek.setDayCellFactory(picker -> new DateCell() {

            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate minDate = dateAankomst.getValue().plusDays(1);
                setDisable(empty || date.compareTo(minDate) < 0);

            }
        });

        dateAankomst.valueProperty().addListener((observable, oldvalue, newvalue) -> {

            LocalDate minDepartureDate = newvalue.plusDays(1);
            if (dateVertrek.getValue() != null && dateVertrek.getValue().isBefore(minDepartureDate)) {

                dateVertrek.setValue(minDepartureDate);
            }

            dateVertrek.setDayCellFactory(picker -> new DateCell() {
                public void updateItem(LocalDate date, boolean empty) {

                    super.updateItem(date, empty);
                    LocalDate minDate = newvalue.plusDays(1);
                    setDisable(empty || date.compareTo(minDate) < 0);

                }
            });

        });

        ComboBox cmbKamerType = new ComboBox();
        cmbKamerType.getItems().addAll("standaard", "familie", "deluxe", "vip");
        cmbKamerType.setId("modifyinput");
        cmbKamerType.setValue(b.getKamerType());



        Button btnGewijzigd = new Button("Wijzigen");
        btnGewijzigd.setId("modifyinput");

        CheckBox chkGewijzigd = new CheckBox("weet u zeker dat u deze boeking wilt wijzigen?");
        chkGewijzigd.setPadding(new Insets(0,0,0,90));
        chkGewijzigd.setAlignment(Pos.CENTER);




        Database db = new Database();

        btnGewijzigd.setOnAction(e -> {

            if(chkGewijzigd.isSelected()){
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

                ModifyDeleteScreen md = new ModifyDeleteScreen(stage, new Scene(new HBox(), 900, 600), db);
                stage.setScene(md.getModifyDeletescene());


            }

            else {


                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Waarschuwing");
                alert.setHeaderText(null);
                alert.setContentText("Bevestig dat u deze boeking wilt wijzigen");

                alert.showAndWait();
                return;
            }




        });





        modifyInput.add(txtVoorNaam, 0, 1);
        modifyInput.add(txtAchterNaam, 0, 2);
        modifyInput.add(txtEmail, 0, 3);
        modifyInput.add(txtTelefoon, 0, 4);
        modifyInput.add(txtAantalPersonen, 0, 5);
        modifyInput.add(dateAankomst, 0, 6);
        modifyInput.add(tot, 0, 7);
        modifyInput.add(dateVertrek, 0, 8);
        modifyInput.add(cmbKamerType, 0, 9);
        modifyInput.add(btnGewijzigd, 0, 10);





      Label btnTerug = new Label();
      btnTerug.setText("Ga Terug");
      btnTerug.setId("btnterug");
      btnTerug.setPrefSize(200,40);
      btnTerug.setAlignment(Pos.CENTER);

      btnTerug.setOnMouseClicked(e->{

        ModifyDeleteViewScreen mdv = new ModifyDeleteViewScreen(stage,new Scene(new HBox(),900,600), b);
        stage.setScene(mdv.getScene());

      });




        bookingTitleContainer.getChildren().addAll(inputTitle);
        modifyInputContainer.getChildren().addAll(modifyInput,chkGewijzigd,btnTerug);

        root.getChildren().addAll(bookingTitleContainer,modifyInputContainer);

        scene.setRoot(root);
        stage.setScene(scene);
        stage.setTitle("Wijzig");

    }

    public Scene getScene() {
        return scene;
    }
}
