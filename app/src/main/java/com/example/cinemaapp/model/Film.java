package com.example.cinemaapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Objects;

@Entity(tableName = "films")
public class Film implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idFilm;
    private String title;
    private String genre;
    private String description;
    private double rating;
    private int imagePath;
    private boolean isFavorite;

    public Film(int idFilm, String title, String genre, String description, double rating, int imagePath, boolean isFavorite) {
        this.idFilm = idFilm;
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.imagePath = imagePath;
        this.isFavorite = isFavorite;
    }

    @Ignore
    public Film(String title, String genre, String description, double rating, int imagePath) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.rating = rating;
        this.imagePath = imagePath;
        isFavorite = false;
    }

    @Ignore
    public Film(String title) {
        this.title = title;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getImagePath() {
        return imagePath;
    }

    public void setImagePath(int imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
