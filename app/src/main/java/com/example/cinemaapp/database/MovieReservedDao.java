package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Query;

import com.example.cinemaapp.model.MovieReserved;

import java.util.List;

@Dao
public interface MovieReservedDao {
    @Query("select * from films")
    List<MovieReserved> getAll();
}
