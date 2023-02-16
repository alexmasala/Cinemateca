package com.example.cinemaapp.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cinemaapp.LoginActivityMADA;
import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.adapters.FilmAdapter;
import com.example.cinemaapp.presenter.HomePresenter;
import com.example.cinemaapp.repository.Repository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomePresenter homeActivityPresenter;
    private Repository repository;

    FirebaseAuth auth;
    FloatingActionButton floatingSignOut;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        homeActivityPresenter = new HomePresenter();


        final View view = inflater.inflate(R.layout.fragment_home, container, false);
        final Fragment pointerSaver = this;

        floatingSignOut = view.findViewById(R.id.floatingSignOut);
        auth = FirebaseAuth.getInstance();

        floatingSignOut.setOnClickListener(v -> {
            if (auth.getCurrentUser() != null) {
                auth.signOut();
                Intent intent = new Intent(getContext(), LoginActivityMADA.class);
                startActivity(intent);
                Toast.makeText(getContext(), R.string.Log_out_successfull, Toast.LENGTH_LONG).show();
            }
        });

        //Add recyclerView
        final RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        final FilmAdapter adapter = new FilmAdapter(this);
        recyclerView.setAdapter(adapter);
        adapter.setFilmlist(Repository.getHardcodedList());

        //Add spinner
        Spinner spinner = (Spinner) view.findViewById(R.id.filter_spinner);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
                R.array.genres_array, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedGenre = spinner.getSelectedItem().toString();
               // String selectedGenre = (String)parent.getItemAtPosition(position).toString();
                List<Film> filteredFilmlist = homeActivityPresenter.filterByGenre(selectedGenre);
                final FilmAdapter adapter = new FilmAdapter(pointerSaver);
                recyclerView.setAdapter(adapter);
                adapter.setFilmlist(filteredFilmlist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        SearchView searchView = (SearchView) view.findViewById(R.id.action_search);

//        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this.getContext(),
//                R.array.genres_array, android.R.layout.simple_spinner_item);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        searchView.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTitle = (String)parent.getItemAtPosition(position);
                List<Film> filteredFilmlist = homeActivityPresenter.filterByTitle(selectedTitle);
                final FilmAdapter adapter = new FilmAdapter(pointerSaver);
                recyclerView.setAdapter(adapter);
                adapter.setFilmlist(filteredFilmlist);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });


        return view;
    }
}
