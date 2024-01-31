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


    // variabele aanmaken voor de stage voor dit scherm die omdat die private is alleen maar te gebruiken is in dit scherm
    private final Stage stage;

    // variabele aanmaken voor de scene van ditscherm die omdat die private is alleen maar te gebruiken is in dit schern
    private final Scene addscene;






    // de variabele stage scene en database meegeven aan de constructor van dit schern.

    public AddScreen(Stage stage, Scene addscene, Database db) {

        // de private variabele ook daadwerkelijk aanroepen in dit scherm zodat ze overal te gebruiken zijn binnen dit schern
        this.stage = stage;
        this.addscene = addscene;



        HBox root = new HBox();
        addscene.getStylesheets().add(HelloApplication.class.getResource("stylesheets/addscreen.css").toString());



        Sidebar sb = new Sidebar(stage,db);

        // een VBox plaats in tegen stelling tot de HBox de items verticaal
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


        // een gridpane aangemaakt voor mijn textvakken, combobox en datepickers. met een gridpane kan je zelf de items toewijzen aan een bepaald opervlak binnen de gridcontainer.
        GridPane voegToeInput = new GridPane();

        // met een vgap zet je tussen de verschillende items binnen de container een ruimte op verticaal gebied.
        voegToeInput.setVgap(10);


        // textfields aangemaakt voor mijn text vakken zodat ik er gegevens in kan schrijven die ik door kan sturen naar mijn database
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

        // met een datepicker kan je een datum uitkiezen ik heb er in mijn geval 2 aangemaakt. 1 voor de aankomstdatum tijdens je hotel bezoek en 1 voor de vertrek datum van je hotel bezoek.
        DatePicker dateAankomst = new DatePicker();
        dateAankomst.setId("voegToeInput");

        Label tot =  new Label();
        tot.setText("T/M");
        tot.setId("tot");

        DatePicker dateVertrek = new DatePicker();
        dateVertrek.setId("voegToeInput");

        // methodes aangemaakt voor aankomst datum en vertrek datum deze functies zorgen ervoor dat binnen de datepicker geen datum voor de huidige datun gekozen kan worden.
        dateAankomst.setDayCellFactory(picker-> new DateCell(){
            public void updateItem(LocalDate date, boolean empty){

                super.updateItem(date,empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || date.compareTo(today)< 0);

            }

        });

        // deze methode zorgt er ook nog eens voor dat er alleen een datum minimaal 1 dag na de geselecteerde aankomst datum geselecteerd kan worden.
       dateVertrek.setDayCellFactory(picker -> new DateCell(){

           public void updateItem(LocalDate date, boolean empty){
              super.updateItem(date,empty);
              LocalDate minDate = dateAankomst.getValue().plusDays(1);
              setDisable(empty || date.compareTo(minDate)< 0 );

           }
       });


       // een methode aangemaakt die de datepickers responsive maken. als er de aankomst datum word aangepast en de vertrek datum zou op de aangepaste aankomst datum vallen, wordt die een dag verschoven.
      dateAankomst.valueProperty().addListener((observable, oldvalue, newvalue)->{

          LocalDate minDepartureDate = newvalue.plusDays(1);
          if(dateVertrek.getValue() != null && dateVertrek.getValue().isBefore(minDepartureDate)){

              dateVertrek.setValue(minDepartureDate);
          }


          // deze methode voegt nog eens als extra toe dat zelfs al is er geen aankomst datum geselecteerd de vertrek datum altijd pas een dag na de huidige datum kan liggen op basis van de datumcel waarden.
          dateVertrek.setDayCellFactory(picker -> new DateCell(){
              public void updateItem(LocalDate date, boolean empty){

                  super.updateItem(date,empty);
                  LocalDate minDate = newvalue.plusDays(1);
                  setDisable(empty|| date.compareTo(minDate) < 0 );

              }
          });

      });

      // een combobox aangemaakt om een kamertype tijdens het invullen van een boeking te selecteren. een combobox is in feite een selectie menu
        ComboBox cmbKamerType = new ComboBox();
        cmbKamerType.getItems().addAll("standaard","familie","deluxe","vip");
        cmbKamerType.setId("voegToeInput");

        // een Button aanmaken voor het opslaan van de ingevoerde gegevens voor een nieuwe boeking in de database.
        Button btnOpslaan = new Button("Opslaan");
        btnOpslaan.setId("voegToeInput");


        // met de setOnAction methode kan je een actie toevoegen aan de button. in dit geval gaat het dus om de boeking op te slaan in de database.
        // voordat de desbtreffende setOnAction methode gebruikt kan worden moet je eerst een event aanroepen die verwijst naar de actie die je gaat uitroepen.
        //in principe kan je de event elke naam geven die je wilt maar in veel gevallen word die aangeroepen met e vanwege duidelijkheid
        btnOpslaan.setOnAction(e->{


       // connectie met de database gelegd binnen de setOnAction methode
            Connection conn = db.getConn();


           // waardes meegegeven aan de textfields
            String voorNaam = txtVoorNaam.getText();
            String achterNaam = txtAchterNaam.getText();
            String email = txtEmail.getText();
            // bij deze textfields een int meegegeven als waarde dus er mag alleen een cijfer ingevoerd worden.
            int telefoon = Integer.parseInt(txtTelefoon.getText());
            int aantalPersonen = Integer.parseInt(txtAantalPersonen.getText());
            // bij de datepickers een timestamp meegegeven als waarde, want in mijn database word ook naar die waardes gevraagd en een timestamp heeft een neutrale date waarde die in elk gebied in de wereld makkelijk gebruikt kan worden.
            Timestamp aankomst = Timestamp.valueOf(dateAankomst.getValue().atStartOfDay());
            Timestamp vertrek = Timestamp.valueOf(dateVertrek.getValue().atStartOfDay());
            String kamerType = cmbKamerType.getValue().toString();



            // hierbij geef ik de waardes van de input uit mijn invoer velden mee aan de opslaanBoeking methode uit mijn database class en zo word de ingevoerde gegevens opgeslagen in de database.
            if (aankomst != null && vertrek != null) { db.opslaanBoeking(voorNaam,achterNaam,email, telefoon, aantalPersonen, aankomst, vertrek, kamerType);}


            // na het opslaan van de gegevens word doormiddel van het aan roepen van het huidige scherm en zijn scene de pagina gerefreshed en zijn de invoer velden weer leeg
            AddScreen a = new AddScreen(stage,new Scene(new HBox(),900,600),db);

            // hierbij wil ik nog toevoegen dat door de stage te koppelen een een getmethode die de scene aanroept van het scherm dat je wilt openen je het huidige scherm vervangt met het nieuwe scherm en dus geen nieuwe stage opent.
            // hierdoor staan er geen meerdere schermen tergelijk open en is er maar 1 stage waarop alle aangeroepen scene zich afspelen.
            stage.setScene(a.getAddscene());


        });


        // de verschillende input velden koppelen aan de juiste plek in de gridcontainer. in dit geval staan alle items onder elkaar dus hoeft alleen de 2e colomn aangepast te worden.
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


    // hier maak ik een get functie aan om die dus later te kunnen aanroepen bij het openen van de scene binnen dezelfde stage.
    public Scene getAddscene() {
        return addscene;
    }




}
