package com.example.cinemaapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapters.FavoriteFilmAdapter;
import com.example.cinemaapp.adapters.FilmAdapter;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.repository.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import static com.example.cinemaapp.view.AddMovieActivity.ADD_MOVIE;

public class AddMovieFragment extends Fragment {

    private FloatingActionButton floatingActionButton;
    private Intent intent;
    public static final int REQUEST_CODE = 200;

    public static final int REQUEST_CODE_EDIT = 300;

    public static final String EDIT_MOVIE = "editMovie";

    public int poz;

    private ListView listView;
    List<Film> filmList = new ArrayList<Film>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fargment_add_movie, container, false);
        final Fragment pointerSaver = this;

        //Add recyclerView
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view_add_movie);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);


//        if (Repository.favoriteList.isEmpty()) {
//            final TextView header = view.findViewById(R.id.no_added_movie_message_header);
//            header.setVisibility(View.VISIBLE);
//            final TextView body = view.findViewById(R.id.no_added_movie_message_body);
//            body.setVisibility(View.VISIBLE);
//        }



// Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fargment_add_movie, container, false);

        //in activity la seminar era pus in onCreate, dupa startActivity

        if (filmList.isEmpty()) {
            final TextView header = view.findViewById(R.id.no_added_movie_message_header);
            header.setVisibility(View.VISIBLE);
            final TextView body = view.findViewById(R.id.no_added_movie_message_body);
            body.setVisibility(View.VISIBLE);
        }

        listView = (ListView) view.findViewById(R.id.lvMovies);


        //set the adapter, etc

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            //de ce punem intentul in onClickView?
            //Aici punem intentul si start activity
            //this is what runs when you click the button
            @Override
            public void onClick(View view) {

                intent = new Intent(getActivity().getApplicationContext(), AddMovieActivity.class);
                Serializable data = intent.getSerializableExtra(AddMovieActivity.ADD_MOVIE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getActivity().getApplicationContext(), AddMovieActivity.class);
                intent.putExtra(EDIT_MOVIE, filmList.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Film films = filmList.get(position);

                final FilmAdapter adapter = (FilmAdapter) listView.getAdapter();

                AlertDialog dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Confirmare stergere")
                        .setMessage("Sigur doriti stergerea?")
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getActivity(), "Nu s-a sters nimic!",
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                filmList.remove(films);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "S-a sters filmul: "+films.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

                dialog.show();

                return true;
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Film films = (Film) data.getSerializableExtra(AddMovieActivity.ADD_MOVIE);

            if (films != null) {

                filmList.add(films);


                FilmAdapter adapter = new FilmAdapter(getActivity(), R.layout.added_movie_listview,
                        filmList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Film films =  filmList.get(position);
//                        System.out.println(notes.getCurNote().toString());
//                        TextView tvMessage = view.findViewById(R.id.mesaj);
//                        if(notes.getCurNote().toString() == "LECTURE" )
//                            tvMessage.setTextColor(Color.GREEN);
//                        else
//                            System.out.println(notes.getCurNote().toString());
//                        if(notes.getCurNote().toString()== "LAB")
//                            tvMessage.setTextColor(Color.BLUE);
//                        else
//                            System.out.println(notes.getCurNote().toString());
//                        if(notes.getCurNote().toString() == "OTHERS")
//                            tvMessage.setTextColor(Color.RED);

                        return view;

                    }
                };

                listView.setAdapter((FilmAdapter) adapter);
            }
        }
        else
        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null) {
            Film films = (Film) data.getSerializableExtra(ADD_MOVIE);
            {
                if (films!=null)
                {
                    filmList.get(poz).setTitle(films.getTitle());
//                    notesList.get(poz).setData(notes.getData());
//                    notesList.get(poz).setMessage(notes.getMessage());
//                    notesList.get(poz).setNotetype(notes.getNotetype());
//                    notesList.get(poz).setCurNote(notes.getCurNote());

                    FilmAdapter adapter = new FilmAdapter(getActivity(), R.layout.added_movie_listview,
                            filmList, getLayoutInflater()){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            Film films =  filmList.get(position);

//                            TextView tvMessage = view.findViewById(R.id.mesaj);
//                            if(notes.getCurNote().toString() == "LECTURE" )
//                                tvMessage.setTextColor(Color.GREEN);
//                            else if(notes.getCurNote().toString()== "LAB")
//                                tvMessage.setTextColor(Color.BLUE);
//                            else
//                            if(notes.getCurNote().toString() == "OTHERS")
//                                tvMessage.setTextColor(Color.RED);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);
                }
            }
        }
    }
}
