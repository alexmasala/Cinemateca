package com.example.cinemaapp.AsyncTaskRunner;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
