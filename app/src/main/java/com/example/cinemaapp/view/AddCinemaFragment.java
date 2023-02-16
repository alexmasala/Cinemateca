package com.example.cinemaapp.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.AsyncTaskRunner.Callback;
import com.example.cinemaapp.R;
import com.example.cinemaapp.adapters.CinemaAdapter;
import com.example.cinemaapp.adapters.FilmAdapter;
import com.example.cinemaapp.model.Cinema;
import com.example.cinemaapp.services.CinemaService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.example.cinemaapp.view.AddMovieActivity.ADD_MOVIE;

public class AddCinemaFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    private Intent intent;
    public static final int REQUEST_CODE = 200;

    public static final int REQUEST_CODE_EDIT = 300;

    public static final String EDIT_MOVIE = "editMovie";

    public int poz;

    private ListView listView;
    List<Cinema> cinemaList = new ArrayList<Cinema>();

    private CinemaService cinemaService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_add_cinema, container, false);
        final Fragment pointerSaver = this;

        //Add recyclerView
//        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view_add_movie);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        final FilmAdapter adapter = new FilmAdapter(this);
//        recyclerView.setAdapter(adapter);


//        if (Repository.favoriteList.isEmpty()) {
//            final TextView header = view.findViewById(R.id.no_added_movie_message_header);
//            header.setVisibility(View.VISIBLE);
//            final TextView body = view.findViewById(R.id.no_added_movie_message_body);
//            body.setVisibility(View.VISIBLE);
//        }



// Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fargment_add_movie, container, false);

        if (cinemaList.isEmpty()) {
            final TextView header = view.findViewById(R.id.no_added_movie_message_header);
            header.setVisibility(View.VISIBLE);
            final TextView body = view.findViewById(R.id.no_added_movie_message_body);
            body.setVisibility(View.VISIBLE);
        }

        listView = (ListView) view.findViewById(R.id.lvCinemas);


        //set the adapter, etc

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {

            //de ce punem intentul in onClickView?
            //Aici punem intentul si start activity
            //this is what runs when you click the button
            @Override
            public void onClick(View view) {

                intent = new Intent(getActivity().getApplicationContext(), AddCinemaActivity.class);
                Serializable data = intent.getSerializableExtra(AddMovieActivity.ADD_MOVIE);
                startActivityForResult(intent, REQUEST_CODE);

//                if (cinemaList.isEmpty()) {
//                    final TextView header = view.findViewById(R.id.no_added_movie_message_header);
//                    header.setVisibility(View.VISIBLE);
//                    final TextView body = view.findViewById(R.id.no_added_movie_message_body);
//                    body.setVisibility(View.VISIBLE);
//                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                poz = position;
                intent = new Intent(getActivity().getApplicationContext(), AddCinemaActivity.class);
                intent.putExtra(EDIT_MOVIE, cinemaList.get(position));
                startActivityForResult(intent, REQUEST_CODE_EDIT);

//                    final TextView header = view.findViewById(R.id.no_added_movie_message_header);
//                    header.setVisibility(View.INVISIBLE);
//                    final TextView body = view.findViewById(R.id.no_added_movie_message_body);
//                    body.setVisibility(View.INVISIBLE);
            }
        });

        cinemaService = new CinemaService(getContext());
        cinemaService.getAll(getAllCallback());

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {

                final Cinema cinemas = cinemaList.get(position);

                final CinemaAdapter adapter = (CinemaAdapter) listView.getAdapter();

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
                                cinemaList.remove(cinemas);
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getActivity(), "S-a sters cinema-ul: "+cinemas.toString(),
                                        Toast.LENGTH_LONG).show();
                                dialogInterface.cancel();
                            }
                        }).create();

//                    final TextView header = view.findViewById(R.id.no_added_movie_message_header);
//                    header.setVisibility(View.VISIBLE);
//                    final TextView body = view.findViewById(R.id.no_added_movie_message_body);
//                    body.setVisibility(View.VISIBLE);

                dialog.show();

                return true;
            }
        });

        return view;
    }

    private Callback<List<Cinema>> getAllCallback() {
        return new Callback<List<Cinema>>() {
            @Override
            public void runResultOnUiThread(
                    List<Cinema> result) {
                if (result != null) {
                    cinemaList.clear();
                    cinemaList.addAll(result);
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Cinema> insertCallback() {
        return new Callback<Cinema>() {
            @Override
            public void runResultOnUiThread(Cinema cinema) {
                if (cinema != null) {
                    cinemaList.add(cinema);
                    notifyAdapter();
                } else {
                    Toast.makeText(getContext(),
                            R.string.failedMessage,
                            Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private Callback<Cinema> updateCallback() {
        return new Callback<Cinema>() {
            @Override
            public void runResultOnUiThread(Cinema cinema) {
                if (cinema != null) {
                    for (Cinema e : cinemaList) {
                        if (e.getIdCinema() == cinema.getIdCinema()) {
                            e.setNameCinema(cinema.getNameCinema());
                            e.setNrSali(cinema.getNrSali());
//                            e.setOras(cinema.getOras());
//                            e.setOra(exam.getOra());
//                            e.setDurataOre(exam.getDurataOre());
                            break;
                        }
                    }
                    notifyAdapter();
                }
            }
        };
    }

    private Callback<Integer> deleteCallback(final int position) {
        return new Callback<Integer>() {
            @Override
            public void runResultOnUiThread(Integer result) {
                if (result != -1) {
                    cinemaList.remove(position);
                    notifyAdapter();
                }
            }
        };
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null) {
            Cinema cinemas = (Cinema) data.getSerializableExtra(AddMovieActivity.ADD_MOVIE);
            cinemaService.insert(cinemas, insertCallback());

            if (cinemas != null) {

                cinemaList.add(cinemas);


                CinemaAdapter adapter = new CinemaAdapter(getActivity(), R.layout.added_cinema_listview,
                        cinemaList, getLayoutInflater()){
                    @NonNull
                    @Override
                    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                        View view = super.getView(position, convertView, parent);

                        Cinema cinemas =  cinemaList.get(position);
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

                listView.setAdapter(adapter);
            }
        }
        else
        if (requestCode == REQUEST_CODE_EDIT && resultCode == Activity.RESULT_OK && data != null) {
            Cinema cinemas = (Cinema) data.getSerializableExtra(ADD_MOVIE);
//            cinemaService.update(cinemas, updateCallback());

            {
                if (cinemas!=null)
                {
                    cinemaList.get(poz).setNameCinema(cinemas.getNameCinema());
//                    notesList.get(poz).setData(notes.getData());
                    cinemaList.get(poz).setNrSali(cinemas.getNrSali());
                    cinemaList.get(poz).setOras(cinemas.getOras());
//                    notesList.get(poz).setCurNote(notes.getCurNote());

                    CinemaAdapter adapter = new CinemaAdapter(getActivity(), R.layout.added_movie_listview,
                            cinemaList, getLayoutInflater()){
                        @NonNull
                        @Override
                        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                            View view = super.getView(position, convertView, parent);

                            Cinema cinemas =  cinemaList.get(position);

//                            TextView tvMessage = view.findViewById(R.id.mesaj);
//                            if(notes.getCurNote().toString() == "LECTURE" )
//                                tvMessage.setTextColor(Color.GREEN);
//                            else if(notes.getCurNote().toString()== "LAB")
//                                tvMessage.setTextColor(Color.BLUE);
//                            else
//                            if(notes.getCurNote().toString() == "OTHERS")
//                                tvMessage.setTextColor(Color.RED);

//                                final TextView header = view.findViewById(R.id.no_added_movie_message_header);
//                                header.setVisibility(View.INVISIBLE);
//                                final TextView body = view.findViewById(R.id.no_added_movie_message_body);
//                                body.setVisibility(View.INVISIBLE);

                            return view;
                        }
                    };
                    listView.setAdapter(adapter);
                }
            }
        }
    }

    private void addAdapter() {
        CinemaAdapter adapter = new CinemaAdapter(getContext(), R.layout.fragment_add_cinema,
                cinemaList, getLayoutInflater());
//        listView.setAdapter(adapter);
    }
    private void notifyAdapter() {
        CinemaAdapter adapter = (CinemaAdapter) listView.getAdapter();
//        adapter.notifyDataSetChanged();
    }
}