package com.example.boeking.hotelboekingsysteem.Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection conn;

    public Database() {
        this.conn = conn;


        String url = "jdbc:mysql://localhost:3306/hotel_boeking_systeem?user=root&password=";

        try {
            conn = DriverManager.getConnection(url);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

public void opslaanBoeking(String voorNaam, String achterNaam, String email, Integer telefoon, Integer aantalPersonen, Timestamp aankomst, Timestamp vertrek, String kamerType){


    try {
        Statement stm = this.conn.createStatement();


        stm.addBatch("INSERT INTO klant(voor_naam,achter_naam,email,telefoon_nummer) VALUES ('"+voorNaam+"','"+achterNaam+"','"+email+"',"+telefoon+")");
        stm.executeBatch();

        ResultSet rs = stm.getGeneratedKeys();
        rs.next();
        int klantId = rs.getInt(1);

        stm.addBatch("INSERT INTO boeking(aantal_personen,aankomst_datum,vertrek_datum,klant_id)VALUES ("+aantalPersonen+",'"+aankomst+"','"+vertrek+"',"+klantId+")");
        stm.executeBatch();


        rs = stm.getGeneratedKeys();
        rs.next();
        int boekingId = rs.getInt(1);

        rs = stm.executeQuery("SELECT  kamer_type_id FROM kamer_type WHERE naam = '"+kamerType+"'");
        rs.next();
        int kamerTypeId = rs.getInt(1);

        rs = stm.executeQuery("SELECT kamer_id FROM kamer WHERE kamer_type_id =" + kamerTypeId);
        rs.next();
        int kamerId = rs.getInt(1);

        stm.addBatch("INSERT INTO boeking_kamer(boeking_id,kamer_id) VALUES ("+boekingId+","+kamerId+") ");
        stm.executeBatch();
       

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}


public ObservableList<Boeking> geefAlleBoekingen(){
       ObservableList<Boeking> lijst = FXCollections.observableArrayList();
    Statement stm = null;
    try {
        stm = this.conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT boeking.boeking_id, klant.voor_naam, klant.achter_naam, klant.email, klant.telefoon_nummer, boeking.aantal_personen, boeking.aankomst_datum, boeking.vertrek_datum,kamer_type.naam FROM klant INNER JOIN boeking ON klant.klant_id = boeking.klant_id INNER JOIN boeking_kamer ON boeking.boeking_id = boeking_kamer.boeking_id INNER JOIN kamer ON boeking_kamer.kamer_id = kamer.kamer_id INNER JOIN kamer_type AS kamer_type ON kamer.kamer_type_id = kamer_type.kamer_type_id" );
while (rs.next()){

    int boekingId = rs.getInt("boeking_id");
    String voorNaam = rs.getString("voor_naam");
    String achterNaam = rs.getString("achter_naam");
    String email = rs.getString("email");
    Integer telefoon = rs.getInt("telefoon_nummer");
    Integer aantalPersonen = rs.getInt("aantal_personen");
    Timestamp aankomst = rs.getTimestamp("aankomst_datum");
    Timestamp vertrek = rs.getTimestamp("vertrek_datum");
    String kamerType = rs.getString("naam");

    Boeking boeking = new Boeking(boekingId,voorNaam,achterNaam,email,telefoon,aantalPersonen,aankomst,vertrek,kamerType);
    lijst.add(boeking);


}

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }











        return lijst;


}

    public Connection getConn() {
        return conn;
    }
}
