package com.example.cinemaapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "cinema")
public class Cinema implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long idCinema;
    private String nameCinema;
    @Ignore private Oras oras;
    private  int idReservation;
    private  String nrSali;

@Ignore
    public Cinema(long idCinema, String nameCinema) {
        this.idCinema = idCinema;
        this.nameCinema = nameCinema;
    }

//    public Cinema(String nameCinema, int idReservation) {
//        this.nameCinema = nameCinema;
//        this.idReservation = idReservation;
//    }


    public Cinema(String nameCinema, int idReservation) {
        this.nameCinema = nameCinema;
        this.idReservation = idReservation;
    }

    @Ignore
    public Cinema(String nameCinema, String nrSali, Oras oras) {
        this.nameCinema = nameCinema;
        this.oras = oras;
        this.nrSali = nrSali;
    }



    @Ignore
    public Cinema(String nameCinema) {
        this.nameCinema = nameCinema;
    }

    public Oras getOras() {
        return oras;
    }

    public void setOras(Oras oras) {
        this.oras = oras;
    }



    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public long getIdCinema() {
        return idCinema;
    }

    public String getNrSali() {
        return nrSali;
    }

    public void setNrSali(String nrSali) {
        this.nrSali = nrSali;
    }

    public void setIdCinema(long idCinema) {
        this.idCinema = idCinema;
    }

    public String getNameCinema() {
        return nameCinema;
    }

    public void setNameCinema(String nameCinema) {
        this.nameCinema = nameCinema;
    }

    @Override
    public String toString() {
        return "Cinema{" +
                "idCinema=" + idCinema +
                ", nameCinema='" + nameCinema + '\'' +
                ", oras=" + oras +
                ", idReservation=" + idReservation +
                ", nrSali='" + nrSali + '\'' +
                '}';
    }

    public enum Oras {BUCURESTI,
        CONSTANTA,
        PLOIESTI,
        TIMISOARA,
        BRASOV ,
        IASI,
        SUCEAVA,
        BUZAU,
        GALATI,
        CRAIOVA,
        SIBIU}

}
