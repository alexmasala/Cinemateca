package com.example.cinemaapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cinemaapp.model.Cinema;

import java.util.List;

    @Dao
    public interface CinemaDao {
        @Insert
        public long insert(Cinema cinema);

        @Query("delete from cinema")
        public int deleteAll();

        @Query("select * from cinema")
        public List<Cinema> getAll();

        @Delete
        public void deleteCinema(Cinema cinema);

        @Query("select * from cinema where idReservation=:idReservation")
        List<Cinema> getCinemaByReservation(String idReservation);

        @Query("select * from cinema where idCinema=:nameOfCinema")
        List<Cinema> getAllByName(String nameOfCinema);

//        int update(Cinema cinema);
//        @Query("Delete from cinema where idCinema = :id1")
//        void deleteWhere(long id1);

//        @Query("select * from examene where tipExam=:tipExam")
//        List<Exam> getAllByCategory(String tipExam);
//        @Update
}
