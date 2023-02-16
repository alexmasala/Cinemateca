package com.example.cinemaapp.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class ReservationOfMovie {
    @Embedded
    public com.example.cinemaapp.model.Reservation reservation;
    @Relation(
            parentColumn = "idReservation",
            entityColumn = "idFilm",
            associateBy = @Junction(ReservationMovie.class)
    )
    public List<Film> listMoviesReserved;
}