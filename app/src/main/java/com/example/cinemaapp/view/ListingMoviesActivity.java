package com.example.cinemaapp.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cinemaapp.R;
import com.example.cinemaapp.adapters.FilmAdapter;
import com.example.cinemaapp.database.CinematecaDB;
import com.example.cinemaapp.model.Cinema;
import com.example.cinemaapp.model.Film;
import com.example.cinemaapp.model.MovieInCinemas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListingMoviesActivity extends AppCompatActivity {


    ListView listView;
    private static final int REQUEST_CODE = 200;
    public static final int REQUEST_CODE_EDIT_MOVIE = 300;
    private static final int REQUEST_CODE_ADD_CINEMA = 210;
    public static final String EDIT_MOVIE = "editMovie";
    CinematecaDB db;
    public int poz;
    List<MovieInCinemas> movieInCinemasList = new ArrayList<>();
    List<Film> films = new ArrayList<>();
    List<Cinema> cinemas = new ArrayList<>();
    List<Cinema> cinemasMovie = new ArrayList<>();
    FloatingActionButton floatingActionButton;
    String[] cinemasNames;
    long[] cinemaIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_movies);
        listView = findViewById(R.id.lvMoviesUser);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        db = CinematecaDB.getInstanta(getApplicationContext());
        movieInCinemasList = db.getMovieInCinemasDao().getMovieInCinemas();
        populateListMovies();
        registerForContextMenu(listView);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), AddMovieActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });

        updateUI();
        cinemas = db.getCinemaDao().getAll();
        updateListCinemas();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Film film = (Film) data.getSerializableExtra(AddMovieActivity.ADD_MOVIE);
            if (film != null) {
                films.add(film);
                movieInCinemasList.addAll(db.getMovieInCinemasDao().getMovieInCinemasById(film.getIdFilm()));
                populateListMovies();
                updateUI();
            }
        } else if (requestCode == REQUEST_CODE_EDIT_MOVIE && resultCode == RESULT_OK && data != null) {
            Film film = (Film) data.getSerializableExtra(AddMovieActivity.ADD_MOVIE);
            {
                if (film != null) {
//                    movieInCinemasList.get(poz).carte.setISBN(book.getISBN());
                    movieInCinemasList.get(poz).film.setTitle(film.getTitle());
//                    movieInCinemasList.get(poz).carte.setGenCarte(book.getGenCarte());
//                    movieInCinemasList.get(poz).carte.setNrCopiiDisponibile(book.getNrCopiiDisponibile());
//                    movieInCinemasList.get(poz).carte.setCopertaURI(book.getCopertaURI());
                    populateListMovies();
                    updateUI();
                }
            }
        }
//        else if (requestCode == REQUEST_CODE_ADD_CINEMA && resultCode == RESULT_OK && data != null) {
//            Cinema cinema = (Cinema) data.getSerializableExtra(AddAuthorActivity.ADD_AUTOR);
//            cinemas.add(autor);
//            updateListCinemas();
//            populateListMovies();
//        }
    }

    private void populateListMovies() {
        films.clear();
        movieInCinemasList.clear();
        movieInCinemasList = db.getMovieInCinemasDao().getMovieInCinemas();
        for (MovieInCinemas m : movieInCinemasList) {
            films.add(m.film);
        }
        for (MovieInCinemas m : movieInCinemasList) {
            cinemasMovie = m.cinemas;
        }
    }

    private void updateListCinemas() {
        cinemasNames = new String[cinemas.size()];
        int i = 0;
        cinemaIds = new long[cinemas.size()];
        for (Cinema c : cinemas) {
            cinemasNames[i] = cinemas.get(i).getNameCinema();
            cinemaIds[i] = cinemas.get(i).getIdCinema();
            i++;
        }
    }

    private void updateUI() {
        FilmAdapter adapter = new FilmAdapter(getApplicationContext(), R.layout.element_movie_list, films, getLayoutInflater()) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView tvMovieTitle = view.findViewById(R.id.titluFilm);
                StringBuilder stringBuilder;
//                Uri uri = Uri.parse(films.get(position).getCopertaURI());
//                ImageView iv = view.findViewById(R.id.ivCoperta);
//                iv.setImageURI(uri);

                stringBuilder = new StringBuilder();
                try {
                    for (Cinema a : movieInCinemasList.get(position).cinemas) {
                        stringBuilder.append(a.getNameCinema());
                        if (movieInCinemasList.get(position).cinemas.indexOf(a) != (movieInCinemasList.get(position).cinemas.size() - 1)) {
                            stringBuilder.append(",");
                        }
                    }
                    tvMovieTitle.setText(stringBuilder.toString());
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                return view;
            }
        };
        listView.setAdapter((ListAdapter) adapter);
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.context_menu_admin, menu);
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        BooksAdapter adapter = (BooksAdapter) listView.getAdapter();
//        boolean[] checkedAuthors = new boolean[cinemasNames.length];
//        switch (item.getItemId()) {
//            case R.id.ctx_marcheaza_returnare:
//                Carte c = adapter.getItem(info.position);
//                int nrCopii = c.getNrCopiiDisponibile() + 1;
//                c.setNrCopiiDisponibile(nrCopii);
//                db.getCartiDao().update(c);
//                adapter.notifyDataSetChanged();
//                break;
//
//            case R.id.ctxaddautor:
//                poz = info.position;
//                AlertDialog.Builder builder = new AlertDialog.Builder(ListareCartiActivity.this);
//                builder.setTitle(R.string.autori_disponibili);
//                builder.setMultiChoiceItems(cinemasNames, checkedAuthors, (dialog, which, isChecked) -> checkedAuthors[which] = isChecked);
//                builder.setPositiveButton(R.string.ok, (dialog, which) -> {
//                    Toast.makeText(getApplicationContext(), R.string.mesaj_dialog_autori_ok, Toast.LENGTH_LONG).show();
//                    for (int i = 0; i < checkedAuthors.length; i++) {
//                        boolean checked = checkedAuthors[i];
//                        if (checked) {
//                            AutorCarte ac = new AutorCarte(cinemaIds[i], adapter.getItem(info.position).getIdCarte());
//                            db.getCarteCuAutoriDao().insert(ac);
//                        }
//                    }
//                    populateListMovies();
//                    updateUI();
//                });
//                builder.setNeutralButton(R.string.adauga_autor, (dialog, which) -> {
//                    Intent intent = new Intent(getApplicationContext(), AddAuthorActivity.class);
//                    startActivityForResult(intent, REQUEST_CODE_ADD_AUTOR);
//                });
//                builder.setNegativeButton(R.string.cancel, (dialog, which) -> Toast.makeText(getApplicationContext(),
//                        R.string.mesaj_dialog_cancel, Toast.LENGTH_LONG).show());
//                AlertDialog newDialog = builder.create();
//                newDialog.show();
//                break;
//
//            case R.id.ctxedit:
//                AdaugaCarteActivity.isUpdate = true;
//                poz = info.position;
//                Intent intent = new Intent(getApplicationContext(), AdaugaCarteActivity.class);
//                intent.putExtra(EDIT_BOOK, adapter.getItem(info.position));
//                startActivityForResult(intent, REQUEST_CODE_EDIT_BOOK);
//                break;
//
//            case R.id.ctxdelete:
//                AlertDialog dialog = new AlertDialog.Builder(ListareCartiActivity.this)
//                        .setTitle(R.string.confirmare_stergere)
//                        .setMessage(R.string.mesaj_stergere)
//                        .setNegativeButton("No", (dialogInterface, which) -> dialogInterface.cancel())
//                        .setPositiveButton("Yes", (dialogInterface, which) -> {
//                            db.getCarteCuAutoriDao().deleteBookById(adapter.getItem(info.position).getIdCarte());
//                            Log.i("DE-STERS", adapter.getItem(info.position).toString());
//                            db.getCartiDao().deleteBook(adapter.getItem(info.position));
//                            adapter.remove(adapter.getItem(info.position));
//                            adapter.notifyDataSetChanged();
//                            Toast.makeText(getApplicationContext(), R.string.carte_stearsa, Toast.LENGTH_LONG).show();
//                            dialogInterface.cancel();
//                        }).create();
//                dialog.show();
//                return true;
//        }
//        return false;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.options_menu_admin, menu);
//        return true;
//    }
//
//    @SuppressLint("NonConstantResourceId")
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.optiuneImprumuturi) {
//            Intent intent = new Intent(getApplicationContext(), VizualizareImprumuturiActivity.class);
//            startActivity(intent);
//            return false;
//        }
//        return true;
//    }
}