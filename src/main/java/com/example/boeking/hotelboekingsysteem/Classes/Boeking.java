package com.example.boeking.hotelboekingsysteem.Classes;

import java.sql.Timestamp;

public class Boeking {

    private Integer boekingId;
    private String voorNaam;
    private String achterNaam;
    private String email;
    private Integer telefoon;
    private Integer aantalPersonen;
    private Timestamp aankomst;
    private Timestamp vertrek;
    private String kamerType;



    public Boeking(Integer boekingId, String voorNaam, String achterNaam, String email, Integer telefoon, Integer aantalPersonen, Timestamp aankomst, Timestamp vertrek, String kamerType) {
        this.boekingId = boekingId;
        this.voorNaam = voorNaam;
        this.achterNaam = achterNaam;
        this.email = email;
        this.telefoon = telefoon;
        this.aantalPersonen = aantalPersonen;
        this.aankomst = aankomst;
        this.vertrek = vertrek;
        this.kamerType = kamerType;
    }

    public Integer getBoekingId() {
        return boekingId;
    }

    public void setBoekingId(Integer boekingId) {
        this.boekingId = boekingId;
    }

    public String getVoorNaam() {
        return voorNaam;
    }

    public void setVoorNaam(String voorNaam) {
        this.voorNaam = voorNaam;
    }

    public String getAchterNaam() {
        return achterNaam;
    }

    public void setAchterNaam(String achterNaam) {
        this.achterNaam = achterNaam;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTelefoon() {
        return telefoon;
    }

    public void setTelefoon(Integer telefoon) {
        this.telefoon = telefoon;
    }

    public Integer getAantalPersonen() {
        return aantalPersonen;
    }

    public void setAantalPersonen(Integer aantalPersonen) {
        this.aantalPersonen = aantalPersonen;
    }

    public Timestamp getAankomst() {
        return aankomst;
    }

    public void setAankomst(Timestamp aankomst) {
        this.aankomst = aankomst;
    }

    public Timestamp getVertrek() {
        return vertrek;
    }

    public void setVertrek(Timestamp vertrek) {
        this.vertrek = vertrek;
    }

    public String getKamerType() {
        return kamerType;
    }

    public void setKamerType(String kamerType) {
        this.kamerType = kamerType;
    }
}
