package com.example.cinemaapp.model;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

public class CinemaWithMovies {
    @Embedded
    public Cinema Cinema;
    @Relation(
            parentColumn = "idCinema",
            entityColumn = "idFilm",
            associateBy = @Junction(CinemaMovie.class)
    )
    public List<Film> films;
}
