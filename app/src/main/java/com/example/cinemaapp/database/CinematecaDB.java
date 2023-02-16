package com.example.cinemaapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.cinemaapp.UtilizatorMADA;
import com.example.cinemaapp.model.Cinema;
import com.example.cinemaapp.model.CinemaMovie;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.Reservation;
import com.example.cinemaapp.model.ReservationMovie;

@Database(entities = {Film.class, Cinema.class, CinemaMovie.class, UtilizatorMADA.class, Reservation.class, ReservationMovie.class}, version = 5, exportSchema = false)
@TypeConverters({com.example.cinemaapp.database.DateConvertor.class, EnumConvertor.class})
public abstract class CinematecaDB extends RoomDatabase {
    private final static String DB_NAME = "cinemateca.db";
    private static com.example.cinemaapp.database.CinematecaDB instanta;

    public static com.example.cinemaapp.database.CinematecaDB getInstanta(Context context) {
        if (instanta == null) {
            synchronized (com.example.cinemaapp.database.CinematecaDB.class) {
                if (instanta == null) {
                    instanta = Room.databaseBuilder(context, com.example.cinemaapp.database.CinematecaDB.class, DB_NAME)
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration().build();
                }
            }
        }
        return instanta;
    }

    public abstract com.example.cinemaapp.database.MovieInCinemasDao getMovieInCinemasDao();

    public abstract com.example.cinemaapp.database.MovieDao getMovieDao();

    public abstract com.example.cinemaapp.database.CinemaDao getCinemaDao();

    public abstract UserDao getUserDao();

    public abstract com.example.cinemaapp.database.ReservationDao getReservationDao();

    public abstract com.example.cinemaapp.database.ReservationOfMoviesDao getReservationOfMoviesDao();

    public abstract com.example.cinemaapp.database.CinemaWithMoviesDao getCinemaWithMoviesDao();
}


