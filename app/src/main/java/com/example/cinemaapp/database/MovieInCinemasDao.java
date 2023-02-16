package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.cinemaapp.model.CinemaMovie;
import com.example.cinemaapp.model.Genre;
import com.example.cinemaapp.model.MovieInCinemas;

import java.util.List;

@Dao
public interface MovieInCinemasDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    long insert(CinemaMovie cm);

    @Transaction
    @Query("SELECT * FROM films")
    List<MovieInCinemas> getMovieInCinemas();

    @Transaction
    @Query("SELECT * FROM films where idFilm=:idFilm")
    List<MovieInCinemas> getMovieInCinemasById(long idFilm);

    @Transaction
    @Query("SELECT * FROM films where title like '%' || :title || '%'")
    List<MovieInCinemas> getMovieInCinemasByName(String title);

    @Query("DELETE from CinemaMovie where idFilm = :id")
    void deleteMovieById(long id);

//    @Query("select * from films where genreFilm=:gen")
//    List<MovieInCinemas> getCartiByGenre(Genre gen);

    @Query("select * from films order by title")
    List<MovieInCinemas> getMoviesOrderedByTitle();
}
