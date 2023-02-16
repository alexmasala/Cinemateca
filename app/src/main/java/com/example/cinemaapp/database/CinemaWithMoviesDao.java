package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.cinemaapp.model.CinemaWithMovies;

import java.util.List;

@Dao
public interface CinemaWithMoviesDao {
    @Query("select * from Cinema where nameCinema=:nameOfCinema")
    List<CinemaWithMovies> getCinemaWithMoviesByName(String nameOfCinema);
}
