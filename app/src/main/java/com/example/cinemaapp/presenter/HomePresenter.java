package com.example.cinemaapp.presenter;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomePresenter {

    List<Film> filmList = Repository.getHardcodedList();


    @RequiresApi(api = Build.VERSION_CODES.N)
    public List<Film> filterByGenre(String genre){
        if (genre.equals("All"))
            return filmList;
//        if (genre.equals("Drama"))
//            return filmList;
//        if (genre.equals("Adventure"))
//            return filmList.;
//        if (genre.equals("Animation"))
//            return filmList;
//        if (genre.equals("Horror"))
//            return filmList;
//        if (genre.equals("Comedy"))
//            return filmList;
//        if (genre.equals("Romance"))
//            return filmList;
//        if (genre.equals("Documentary"))
//            return filmList;

//        List<Film> result = filmList.stream().filter(f -> genre.equals("All") || f.getGenre().equals(genre)).collect(Collectors.toList());
//        return result;

        List<Film> filteredFilmList = new ArrayList<>();

        for (Film f : filmList){

            if(f.getGenre().equals(genre)){

                filteredFilmList.add(f);
            }
        }

        return filteredFilmList;


//        return filmList.stream().filter(film -> genre.equals(film.getGenre())).collect(Collectors.toList());
    }

    public List<Film> filterByTitle(String title){
        if (title.equals("All"))
            return filmList;
        else if (title.equals("Drama"))
            return filmList;
        else if (title.equals("Adventure"))
            return filmList;
        else if (title.equals("Animation"))
            return filmList;
        else if (title.equals("Horror"))
            return filmList;
        else if (title.equals("Comedy"))
            return filmList;
        else if (title.equals("Romance"))
            return filmList;
        else if (title.equals("Documentary"))
            return filmList;

        List<Film> filteredFilmList = new ArrayList<>();

        for (Film f : filmList){

            if(f.getTitle().equals(title)){

                filteredFilmList.add(f);
            }
        }

        return filteredFilmList;
    }
 }
