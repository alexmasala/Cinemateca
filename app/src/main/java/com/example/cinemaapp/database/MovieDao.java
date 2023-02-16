package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cinemaapp.model.Film;

@Dao
    public interface MovieDao {
        @Insert
        long insert(Film f);

        @Query("delete from films")
        void deleteAll();

        @Delete
        void deleteMovie(Film f);

        @Update
        void update(Film f);
    }
