package com.example.cinemaapp.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MovieReserved {
    @Embedded
    public Film film;
    @Relation(
            parentColumn = "idFilm",
            entityColumn = "idReservation",
            associateBy = @Junction(ReservationMovie.class)
    )
    public List<Film> listReservationMovie;
}
