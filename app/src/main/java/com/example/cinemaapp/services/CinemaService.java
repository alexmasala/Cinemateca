package com.example.cinemaapp.services;

import android.content.Context;

import com.example.cinemaapp.AsyncTaskRunner.AsyncTaskRunner;
import com.example.cinemaapp.AsyncTaskRunner.Callback;
import com.example.cinemaapp.database.CinemaDao;
import com.example.cinemaapp.database.DatabaseManager;
import com.example.cinemaapp.model.Cinema;

import java.util.List;
import java.util.concurrent.Callable;

public class CinemaService {

    private final CinemaDao cinemaDao;
    private final AsyncTaskRunner asyncTask;

    public CinemaService(Context context) {
        cinemaDao = DatabaseManager.getInstanta(context)
                .getCinemaDao();
        asyncTask = new AsyncTaskRunner();
    }

//    public void getAllByCategory(final String format, final Callback<List<Cinema>> callback) {
//        Callable<List<Cinema>> callable = new Callable<List<Cinema>>() {
//            @Override
//            public List<Cinema> call() {
//                if (format == null || format.trim().isEmpty()) {
//                    return null;
//                }
//                return cinemaDao.getAllByCategory(format);
//            }
//        };
//
//        asyncTask.executeAsync(callable, callback);
//    }

    public void getAll(Callback<List<Cinema>> callback) {
        Callable<List<Cinema>> callable = new Callable<List<Cinema>>() {
            @Override
            public List<Cinema> call() {
                return cinemaDao.getAll();
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

    public void insert(final Cinema cinema,
                       Callback<Cinema> callback) {
        Callable<Cinema> callable = new Callable<Cinema>() {
            @Override
            public Cinema call() {
                if (cinema == null) {
                    return null;
                }
                long id = cinemaDao.insert(cinema);
                if (id == -1) {
                    return null;
                }
                cinema.setIdCinema((int) id);
                return cinema;
            }
        };
        asyncTask.executeAsync(callable, callback);
    }

//    public void update(final Cinema cinema,
//                       Callback<Cinema> callback) {
//        Callable<Cinema> callable = new Callable<Cinema>() {
//            @Override
//            public Cinema call() {
//                if (cinema == null) {
//                    return null;
//                }
//                int count = cinemaDao.update(cinema);
//                if (count != 1) {
//                    return null;
//                }
//                return cinema;
//            }
//        };
//        asyncTask.executeAsync(callable, callback);
//    }

    public void delete(final Cinema cinema,
                       Callback<Integer> callback) {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() {
                if (cinema == null) {
                    return 1;
                }
                return cinemaDao.deleteAll();
            }
        };
        asyncTask.executeAsync(callable, callback);
    }
}
