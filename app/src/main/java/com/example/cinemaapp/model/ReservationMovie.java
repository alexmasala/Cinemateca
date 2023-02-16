package com.example.cinemaapp.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"idReservation", "idFilm"})
public class ReservationMovie {
    private long idFilm;
    private long idReservation;

    public ReservationMovie(long idReservation, long idFilm) {
        this.idFilm = idFilm;
        this.idReservation = idReservation;
    }

    public long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(long idFilm) {
        this.idFilm = idFilm;
    }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }
}
