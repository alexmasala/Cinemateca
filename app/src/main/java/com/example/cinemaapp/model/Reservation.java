package com.example.cinemaapp.model;

import android.graphics.Bitmap;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cinemaapp.UtilizatorMADA;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

//@Entity(tableName = "reservations", foreignKeys = @ForeignKey(entity = UtilizatorMADA.class, parentColumns = "id", childColumns = "idUser"))
@Entity(tableName = "reservations")
public class Reservation implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private long idReservation;
//    private long idUser;
    @Embedded private Film film;
    @Ignore private Time startTime;
    @Ignore private List<Integer> places;
    @Ignore private Bitmap codeQR;

    @Ignore
    public Reservation(){}

    public Reservation(long idReservation, Film film) {
        this.idReservation = idReservation;
//        this.idUser = idUser;
        this.film = film;
    }

    @Ignore
    public Reservation(Film film, Time startTime, List<Integer> places, Bitmap codeQR) {
        this.film = film;
        this.startTime = startTime;
        this.places = places;
        this.codeQR = codeQR;
    }

    public long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(long idReservation) {
        this.idReservation = idReservation;
    }

//    public long getIdUser() {
//        return idUser;
//    }
//
//    public void setIdUser(long idUser) {
//        this.idUser = idUser;
//    }

    public void setPlaces(List<Integer> places) {
        this.places = places;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Bitmap getCodeQR() {
        return codeQR;
    }

    public void setCodeQR(Bitmap codeQR) {
        this.codeQR = codeQR;
    }

    public List<Integer> getPlaces() { return places; }

    @Override
    public String toString() {
        return "Reservation{" +
                "film=" + film +
                ", startTime=" + startTime +
                ", codeQR='" + codeQR + '\'' +
                '}';
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(film, that.film) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(places, that.places);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(film, startTime, places);
    }
}
