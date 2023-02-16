package com.example.cinemaapp.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class MovieInCinemas {

    @Embedded
    public com.example.cinemaapp.model.Film film;
    @Relation(
            parentColumn = "idFilm",
            entityColumn = "idCinema",
            associateBy = @Junction(CinemaMovie.class)
    )
    public List<Cinema> cinemas;
}
