package com.example.boeking.hotelboekingsysteem.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection conn;

    public Database() {

        //  een connectie vaststellen
        this.conn = conn;

// de juiste database gekoppeld aan mijn applicatie
        String url = "jdbc:mysql://localhost:3306/hotel_boeking_systeem?user=root&password=";

        try {
            //

            // de juiste connectie aanroepen
            conn = DriverManager.getConnection(url);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // methode voor het opslaan van een boeking in het systeem. deze word gebruikt in mijn voegtoescherm
    public void opslaanBoeking(String voorNaam, String achterNaam, String email, Integer telefoon, Integer aantalPersonen, Timestamp aankomst, Timestamp vertrek, String kamerType) {


        try {

            // een sql statement aanmaken en koppelen aan de connectie
            Statement stm = this.conn.createStatement();

// eerst worden de gegevens die horen bij het klanttabel uit mijn database aan dat tabel toegevoegd
            stm.addBatch("INSERT INTO klant(voor_naam,achter_naam,email,telefoon_nummer) VALUES ('" + voorNaam + "','" + achterNaam + "','" + email + "'," + telefoon + ")");
            stm.executeBatch();

            // door middel van deze result set word gekeken of er klant_id bestaat zodat daarna de boeking querry kan worden uitgevoerd
            ResultSet rs = stm.getGeneratedKeys();
            rs.next();
            int klantId = rs.getInt(1);

            // hier word de querry uitgevoerd die de juiste gegevens toevoegd aan het boeking tabel op basis op het hiervoor gecreeërde klant id en zijn gegevens
            stm.addBatch("INSERT INTO boeking(aantal_personen,aankomst_datum,vertrek_datum,klant_id)VALUES (" + aantalPersonen + ",'" + aankomst + "','" + vertrek + "'," + klantId + ")");
            stm.executeBatch();

// door deze result set word er gekeken of er een boeking_id is zodat later deze kan worden gekoppeld aan het juiste kamer_id.
            rs = stm.getGeneratedKeys();
            rs.next();
            int boekingId = rs.getInt(1);

            // hier word de de juiste kamertype geselecteerd doormiddel van het kamertype id.
            rs = stm.executeQuery("SELECT  kamer_type_id FROM kamer_type WHERE naam = '" + kamerType + "'");
            rs.next();

            // het kamer type id wordt hier als variabele vast gesteld om in de volgende querry gebruikt te kunnen worden
            int kamerTypeId = rs.getInt(1);

            // bij deze querry word de juiste kamer geselecteerd op basis van het juiste kamer_type_id
            rs = stm.executeQuery("SELECT kamer_id FROM kamer WHERE kamer_type_id =" + kamerTypeId);
            rs.next();
            int kamerId = rs.getInt(1);

            // hier word de kamer_id gekoppeld aan het juiste boeking_id in het boeking_kamer tabel
            stm.addBatch("INSERT INTO boeking_kamer(boeking_id,kamer_id) VALUES (" + boekingId + "," + kamerId + ") ");
            stm.executeBatch();


            // een fout afhandeling die bij elke statement toegepast moet worden
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    // een methode met daarin querries die de boekingen  moeten kunnen laten weergeven op hetscherm
    public ObservableList<Boeking> geefAlleBoekingen() {

        // een observable list aangemaakt zodat de juistegegevens van de boeking mee kunnen worden gegeven aan de lijst
        // deze observable list zorgt er ook voor dat als een boeking gewijzigd word dit ook gelijk in de lijst word eengepast en dus ook word weergeven op het scherm waarin deze methode is aangeroepen
        ObservableList<Boeking> lijst = FXCollections.observableArrayList();
        Statement stm = null;
        try {

            //statement aanmaken
            stm = this.conn.createStatement();

            // een resultset querry aanmaken met daarin een join methode die alle tabellen uit de database met elkaar verbind zodat alle gegevens die getoond moeten worden van een boeking getoond kunnen worden.
            ResultSet rs = stm.executeQuery("SELECT boeking.boeking_id, klant.voor_naam, klant.achter_naam, klant.email, klant.telefoon_nummer, boeking.aantal_personen, boeking.aankomst_datum, boeking.vertrek_datum,kamer_type.naam FROM klant INNER JOIN boeking ON klant.klant_id = boeking.klant_id INNER JOIN boeking_kamer ON boeking.boeking_id = boeking_kamer.boeking_id INNER JOIN kamer ON boeking_kamer.kamer_id = kamer.kamer_id INNER JOIN kamer_type AS kamer_type ON kamer.kamer_type_id = kamer_type.kamer_type_id");
            // een while loop aanmaken die de resultset doorspit en boekingen laat zien totdat er geen volgende boeking meer staat in de resultset
            while (rs.next()) {

                // datatypes meegeven aan de variabele uit de Boeking class en die ook koppelen aan de juiste kolom naam uit mijn database
                int boekingId = rs.getInt("boeking_id");
                String voorNaam = rs.getString("voor_naam");
                String achterNaam = rs.getString("achter_naam");
                String email = rs.getString("email");
                Integer telefoon = rs.getInt("telefoon_nummer");
                Integer aantalPersonen = rs.getInt("aantal_personen");
                Timestamp aankomst = rs.getTimestamp("aankomst_datum");
                Timestamp vertrek = rs.getTimestamp("vertrek_datum");
                String kamerType = rs.getString("naam");

                // het Boeking  object ook daadwerkelijk obhalen
                Boeking boeking = new Boeking(boekingId, voorNaam, achterNaam, email, telefoon, aantalPersonen, aankomst, vertrek, kamerType);
                //de class boeking meegeven aan de lijst
                lijst.add(boeking);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lijst;
    }

    public void wijzigBoeking(Boeking b) {

        try {

            Statement stm = this.conn.createStatement();

            // Eerst worden de klantgegevens geupdate die bij het desbetreffende boeking_id horen.
            stm.executeUpdate("UPDATE klant SET voor_naam='" + b.getVoorNaam() + "', achter_naam='" + b.getAchterNaam() + "', email='" + b.getEmail() + "', telefoon_nummer=" + b.getTelefoon() + " WHERE klant_id IN (SELECT klant_id FROM boeking WHERE boeking_id=" + b.getBoekingId() + ")");

           // Daarna word het klant id geselecteerd dat gekoppeld is aan het email van de boeking die gewijzigd word
            // dit is om de juiste klantgegevens te koppelen en er voor te zorgen dat er geen 2 boekingen worden gewijzigd als er meer klanten met dezelfde email zijn.
            ResultSet rs = stm.executeQuery("SELECT klant_id FROM klant WHERE email='" + b.getEmail() + "' ");

            rs.next();

            // een variabele maken van klant_id om deze te koppelen aan de juiste boeking//
            int newKlantId = rs.getInt(1);

            // nu kunnen de juiste gegevens uit het boeking tabel gewijzigd worden.
            stm.executeUpdate("UPDATE boeking SET aantal_personen=" + b.getAantalPersonen() + ", aankomst_datum='" + b.getAankomst() + "', vertrek_datum='" + b.getVertrek() + "', klant_id=" + newKlantId + " WHERE boeking_id=" + b.getBoekingId());

            // daarna moet het juiste kamer_id geselecteerd worden op basis van geselecteerde kamer type naam. dat gebeurt in de querry hieronder.
            rs = stm.executeQuery("SELECT kamer_id FROM kamer WHERE kamer_type_id IN (SELECT kamer_type_id FROM kamer_type WHERE naam = '" + b.getKamerType() + "')");
            rs.next();

            // een variabele maken van het gewijzigde kamer_id om deze aan de juiste boeking_id te koppelen in Boeking_kamer tabel.
            int newKamerId = rs.getInt(1);

            // hier word de Boeking_kamer tabel geupdate
            stm.executeUpdate("UPDATE boeking_kamer SET kamer_id=" + newKamerId + " WHERE boeking_id=" + b.getBoekingId());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void verwijderBoeking(Boeking b) {

        try {

            // er word een tijdelijk tabel gecreeërd zodat de juiste klant gegevens verwijderd kunnen worden. zonder deze methode lukte mij het niet om klant gegevens te verwijderen.
            //de reden hiervoor was de foreignkeys die hiet niet toestonden, of als ik de klant querry na de boeking querry zou doen dan werd de klant gegevens niet verwsijderd ivm het niet herkennen van het juiste gekoppelde boeking nummer.
            // met het aanmaken van een tijdelijk tabel lukte het dus wel om de klant gegevens voor de boeking te verwijderen
            PreparedStatement stm = this.conn.prepareStatement("CREATE TEMPORARY TABLE temp_klant AS SELECT klant_id FROM boeking WHERE boeking_id = ?");
            // juiste boeking_ID ophalen zodat de juiste boeking word verwijderd.
            stm.setInt(1, b.getBoekingId());
            stm.executeUpdate();


            //als eerste moet de boeking_kamer tabel gegevens van de desbetreffende boeking verwijderd worden. zodat de boeking en de kamer niet meer gelinkt is.
            stm = this.conn.prepareStatement("DELETE FROM boeking_kamer WHERE boeking_id = ?");
            stm.setInt(1, b.getBoekingId());
            stm.executeUpdate();

           // nu kunnen de boeking gegevens uit het boeking tabel verwijderd worden
            stm = this.conn.prepareStatement("DELETE FROM boeking WHERE boeking_id = ?");
            stm.setInt(1, b.getBoekingId());
            stm.executeUpdate();

           // doormiddel van het tijdelijk tabel kunnen nu de juiste klant gegevens verwijderd worden die bij de zojuist verwijderde boeking horen.
            stm = this.conn.prepareStatement("DELETE FROM klant WHERE klant_id IN (SELECT klant_id FROM temp_klant)");
            stm.executeUpdate();
            // het tijdelijk tabel weer opheven nadat alles van de desbetreffende boeking verwijderd is.
            stm = this.conn.prepareStatement("DROP TEMPORARY TABLE IF EXISTS temp_klant");
            stm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public Connection getConn() {
        return conn;
    }
}

