package com.example.cinemaapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cinemaapp.model.Cinema;
import com.example.cinemaapp.model.Reservation;

@Database(entities = {Cinema.class, Reservation.class}, version = 2, exportSchema = false)
@TypeConverters({DateConvertor.class})
public abstract class DatabaseManager extends RoomDatabase {

    public static final String DB_NAME = "cinemas.db";
    private static DatabaseManager instanta;

    public static DatabaseManager getInstanta(Context context){
        if(instanta == null)
            instanta = Room.databaseBuilder(context, DatabaseManager.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        return instanta;
    }

    public abstract CinemaDao getCinemaDao();
    public abstract ReservationDao getReservationDao();
}