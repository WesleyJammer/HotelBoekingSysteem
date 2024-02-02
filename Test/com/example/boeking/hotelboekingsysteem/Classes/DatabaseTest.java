package com.example.boeking.hotelboekingsysteem.Classes;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

// dit is mijn test javafile om mijn database functies te testen.

class DatabaseTest {


    // met deze test test ik of een boeking doormiddel van mijn opslaamBoeking methode ook daadwerkelijk word opgeslagen.
    // deze test is sucesvol want er word ook daadwerkelijk een boeking in mijn database opgeslagen met de gegevens die ik hieronder aan mijn opslaan methode meegeef
    @Test
    void opslaanBoeking() {

      Database db = new Database();

      db.opslaanBoeking("John","dirk","e@mail.com",766655,2,Timestamp.valueOf("2024-01-29 10:00:00"),Timestamp.valueOf("2024-01-30 10:00:00"),"standaard");




    }


    // hiermee test ik of ik een boeking doormiddel van mijn geefAlleBoekingen methode ook daadwerkelijk een boeking kan inzien

    @Test
    void geefAlleBoekingen() {

        Database db = new Database();

        // eerst slaan we weer een boeking op.
        db.opslaanBoeking("John","dirk","e@mail.com",766655,2,Timestamp.valueOf("2024-01-29 10:00:00"),Timestamp.valueOf("2024-01-30 10:00:00"),"standaard");

// nu maken we een observable list aan zodat de boeking in een lijst opgeslagen kan worden
        ObservableList<Boeking> boekingen = db.geefAlleBoekingen();

        assertFalse(boekingen.isEmpty());

        // we halen de 7e(0 telt ook als cijfer) boeking hier op uit de database en de test werkt want alleen door de juiste gegevens hieronder mee te geven werkt de test anders komt er een foutmelding.
        Boeking b = boekingen.get(1);
        assertEquals("John", b.getVoorNaam());
        assertEquals("dirk", b.getAchterNaam());
        assertEquals("e@mail.com", b.getEmail());
        assertEquals(766655, b.getTelefoon());
        assertEquals(2, b.getAantalPersonen());
        assertEquals(Timestamp.valueOf("2024-01-29 10:00:00"), b.getAankomst());
        assertEquals(Timestamp.valueOf("2024-01-30 10:00:00"), b.getVertrek());



    }




// bij deze test word er gekeken of ik met de wijzig boeking methode ook daadwerkelijk een boeking kan wijzigen.
    @Test
    void wijzigBoeking() {
        Database db = new Database();

        ObservableList<Boeking> boekingen = db.geefAlleBoekingen();


        // hier word weer de 7e boeking opgehaald en als je hieronder andere gegevens invult dan er eerst stonden en dan de test runt dan zie je in de database de andere gegevens verschijnen.
        Boeking b = boekingen.get(0);
        b.setVoorNaam("Pieter");
        b.setAchterNaam("Aad");
        b.setEmail("r@mail.com");
        b.setTelefoon(34787);
        b.setAantalPersonen(4);
        b.setAankomst(Timestamp.valueOf("2024-01-30 00:00:00"));
        b.setVertrek(Timestamp.valueOf("2024-01-31 00:00:00"));
        b.setKamerType("familie");

        db.wijzigBoeking(b);



    }


    // bij deze test word er een boeking verwijderd
    @Test
    void verwijderBoeking() {

        Database db = new Database();

        ObservableList<Boeking> boekingen = db.geefAlleBoekingen();

        // hier word de 1e boeking uit de database opgehaald
        Boeking b = boekingen.get(0);

        // hier word de hierboven opgehaalde boeking verwijderd. als je de test runt moet je de database controleren dan zie je dat de 1e boeking eruit word gehaald.
        db.verwijderBoeking(b);


    }
}