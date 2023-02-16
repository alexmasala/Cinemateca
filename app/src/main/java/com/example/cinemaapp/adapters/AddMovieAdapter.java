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
import com.example.cinemaapp.model.Film;

import java.util.List;

public class AddMovieAdapter extends ArrayAdapter<Film> {
    private Context context;
    private int resource;
    private List<Film> filmlist;
    private LayoutInflater layoutInflater;

    public AddMovieAdapter(@NonNull Context context,int resource,List<Film> filmlist, LayoutInflater layoutInflater) {
        super(context, resource, filmlist);
        this.filmlist = filmlist;
        this.layoutInflater = layoutInflater;
        this.context = context;
        this.resource = resource;
    }

    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(resource, parent, false);
        Film film = filmlist.get(position);
        if (film != null) {
            TextView tvTitluFilm = view.findViewById(R.id.titleMovie);
            tvTitluFilm.setText(film.getTitle());
//            TextView tvGen = view.findViewById(R.id.gen);
//            tvGen.setText(String.valueOf(carte.getGenCarte()));
//            ImageView cover = view.findViewById(R.id.ivCoperta);
//            cover.setImageURI(Uri.parse(carte.getCopertaURI()));
        }
        return view;
    }

}
