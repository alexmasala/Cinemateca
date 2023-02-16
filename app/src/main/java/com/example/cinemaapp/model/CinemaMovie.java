package com.example.cinemaapp.model;

import androidx.room.Entity;

@Entity(primaryKeys = {"idFilm", "idCinema"})
public class CinemaMovie {
    private long idCinema;
    private long idFilm;

    public CinemaMovie(long idCinema, long idFilm) {
        this.idCinema = idCinema;
        this.idFilm = idFilm;
    }

    public long getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(long idCinema) {
        this.idCinema = idCinema;
    }

    public long getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(long idFilm) {
        this.idFilm = idFilm;
    }
}
