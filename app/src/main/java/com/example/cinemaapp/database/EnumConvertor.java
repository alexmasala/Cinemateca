package com.example.cinemaapp.database;

import androidx.room.TypeConverter;

import com.example.cinemaapp.model.Genre;

public class EnumConvertor {
    @TypeConverter
    public static String fromGenreToString(Genre genre) {
        return genre != null
                ? genre.toString()
                : null;
    }

    @TypeConverter
    public static Genre fromStringToGenre(String genre) {
        return genre != null
                ? Genre.valueOf(genre)
                : null;
    }
}
