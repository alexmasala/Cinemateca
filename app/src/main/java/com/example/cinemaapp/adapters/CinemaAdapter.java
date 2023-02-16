package com.example.cinemaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Cinema;

import java.util.List;

import static com.example.cinemaapp.R.id.titleCinema;

public class CinemaAdapter extends ArrayAdapter<Cinema> {
    private Context context;
    private int resource;
    private List<Cinema> cinemaList;
    private LayoutInflater layoutInflater;

    public CinemaAdapter(@NonNull Context context, int resource, List<Cinema> cinemaList, LayoutInflater layoutInflater) {
        super(context, resource, cinemaList);
        this.context = context;
        this.resource = resource;
        this.cinemaList = cinemaList;
        this.layoutInflater = layoutInflater;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = layoutInflater.inflate(resource, parent, false);
        Cinema cinemas = cinemaList.get(position);

        if (cinemas!=null)
        {
            TextView id1 = view.findViewById(R.id.titleCinema);
            id1.setText(cinemas.getNameCinema());

//            TextView id2 = view.findViewById(R.id.data);
//            id2.setText(cinemas.getData().toString());
//
            TextView id3 = view.findViewById(R.id.nrSali);
            id3.setText(cinemas.getNrSali());

            TextView tv4 = view.findViewById(R.id.oras);
            tv4.setText(String.valueOf(cinemas.getOras()));
//
//            TextView tv5 = view.findViewById(R.id.curricularNote);
//            tv5.setText(cinemas.getCurNote().toString());
        }

        return view;
    }

}
