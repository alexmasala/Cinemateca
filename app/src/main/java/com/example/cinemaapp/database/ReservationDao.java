package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemaapp.model.Reservation;

import java.util.List;

@Dao
public interface ReservationDao {

    @Insert
    long insert(Reservation r);

//    @Query("SELECT * FROM reservations where idUser=:userId")
//    List<Reservation> getAllReservationsForUser(long userId);

    @Query("SELECT * FROM reservations where idReservation=:id")
    Reservation getReservationById(long id);

//    @Query("delete from reservations where idUser=:id")
//    void deleteAllForUser(long id);


//    @Query("delete from ReservationMovie where idReservation=:id")
//    void deleteAllIC(long id);

    @Delete
    void delete(Reservation r);

    @Update
    void update(Reservation r);

    @Query("select * from reservations")
    public List<Reservation> getAll();

    @Query("delete from reservations")
    public void deleteAll();

    @Delete
    public void deleteReservation(Reservation reservation);
}
