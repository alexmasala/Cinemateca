package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.cinemaapp.model.ReservationMovie;

import java.util.List;

@Dao
public interface ReservationOfMoviesDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ReservationMovie rm);

//    @Transaction
//    @Query("SELECT * FROM reservations where idUser = :id")
//    List<ReservationMovie> getReservationOfMoviesByUserId(long id);
//
//    @Transaction
//    @Query("SELECT * FROM reservations")
//    List<ReservationMovie> getReservationOfMovies();
//
//    @Transaction
//    @Query("SELECT * FROM reservations where idReservation = :id")
//    ReservationMovie geetReservationOfMoviesByRservationId(long id);
}