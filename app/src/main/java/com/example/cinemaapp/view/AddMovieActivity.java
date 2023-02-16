package com.example.cinemaapp.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;
import com.example.cinemaapp.database.CinematecaDB;
import com.example.cinemaapp.model.Film;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddMovieActivity extends AppCompatActivity {
//
//    EditText etMovieTitle;
//    EditText etNrCopii;
//    EditText etISBN;
//    Spinner spinner;
//    Button btnAddImagine;
//    Button btnSave;
//    Intent intent;
//    ImageView imageView;
//    public Uri bookCoverUri = Uri.EMPTY;
//    public static final String ADD_MOVIE = "addMovie";
//    public static final int GALLERY_REQUEST_CODE = 105;
//    public static boolean isUpdate = false;
//    private long editMovieId;
//    private CinematecaDB dbInstance;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_add_movie);
//        initializeUI();
//        dbInstance = CinematecaDB.getInstanta(getApplicationContext());
//        intent = getIntent();
////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.add_book_genre, R.layout.support_simple_spinner_dropdown_item);
////        spinner.setAdapter(adapter);
//
////        if (intent.hasExtra(ListingMoviesActivity.EDIT_MOVIE)) {
//        if (intent.hasExtra(AddMovieFragment.EDIT_MOVIE)) {
//            populeazaCampuri();
//        }
//
//        btnSave.setOnClickListener(v -> {
//            if (etMovieTitle.getText().toString().isEmpty()) {
//                etMovieTitle.setError(getString(R.string.introduce_movie_title));
//            }
////            else if (etISBN.getText().toString().isEmpty()) {
////                etISBN.setError(getString(R.string.introdu_isbn));
////            }
////            else if (etNrCopii.getText().toString().isEmpty()) {
////                etNrCopii.setError(getString(R.string.introdu_nr_de_copii_disponibile));
////            }
//            else {
////                Carte carte = new Carte(etMovieTitle.getText().toString(), etISBN.getText().toString(),
////                        Gen.valueOf(spinner.getSelectedItem().toString()), Integer.parseInt(etNrCopii.getText().toString()), bookCoverUri.toString());
//
//                Film film = new Film(etMovieTitle.getText().toString());
//
//                if (isUpdate) {
//                    film.setIdFilm((int) editMovieId);
//                    dbInstance.getMovieDao().update(film);
//                } else {
//                    film.setIdFilm((int) dbInstance.getMovieDao().insert(film));
//                }
//
//                intent.putExtra(ADD_MOVIE, film);
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });
//
////        btnAddImagine.setOnClickListener(v -> {
////            Intent gallery = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////            startActivityForResult(gallery, GALLERY_REQUEST_CODE);
////        });
//    }
//
////    @Override
////    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
////        super.onActivityResult(requestCode, resultCode, data);
////        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
////            if (data != null) {
////                Uri contentUri = data.getData();
////                this.bookCoverUri = contentUri;
////                this.imageView.setImageURI(contentUri);
////            }
////        }
////    }
//
//    private void populeazaCampuri() {
//        Film film = (Film) intent.getSerializableExtra(ListingMoviesActivity.EDIT_MOVIE);
//        isUpdate = true;
//        if (film != null) {
//            editMovieId = film.getIdFilm();
////            etISBN.setText(carte.getISBN());
//            etMovieTitle.setText(film.getTitle());
////            etNrCopii.setText(String.valueOf(carte.getNrCopiiDisponibile()));
////            ArrayAdapter<String> adaptor = (ArrayAdapter<String>) spinner.getAdapter();
////            for (int i = 0; i < adaptor.getCount(); i++)
////                if (adaptor.getItem(i).equals(String.valueOf(carte.getGenCarte()).toUpperCase())) {
////                    spinner.setSelection(i);
////                    break;
////                }
////            imageView.setImageURI(Uri.parse(carte.getCopertaURI()));
//        }
//    }
//
//    private void initializeUI(){
////        etISBN = findViewById(R.id.etISBN);
//        etMovieTitle = findViewById(R.id.editMovieTitle);
////        etNrCopii = findViewById(R.id.etNrCopii);
////        spinner = findViewById(R.id.spinnerGenre);
////        btnAddImagine = findViewById(R.id.btnAddCoperta);
////        btnSave = findViewById(R.id.btnSalvare);
////        imageView = findViewById(R.id.bookCover);
//
//    }




    //me and andra's version
    public static final String ADD_MOVIE = "addMovie";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);



//        final Spinner spinnerNoteType = findViewById(R.id.spinnerJournal);

        //Leg Spinner-Enum
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
//                R.array.add_journal_note, R.layout.support_simple_spinner_dropdown_item);
//        spinnerNoteType.setAdapter(adapter);

        final EditText etMovieTitle = findViewById(R.id.editMovieTitle);
//        final EditText etDate = findViewById(R.id.editTextDate);
//        final EditText etMessage = findViewById(R.id.editTextMessage);
//        final String DATE_FORMAT = "MM/dd/yyyy";
//
//        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final Intent intent = getIntent();

        if(intent.hasExtra(AddMovieFragment.EDIT_MOVIE)){

            Film film = (Film) intent.getSerializableExtra(AddMovieFragment.EDIT_MOVIE);

            etMovieTitle.setText(film.getTitle());
//            etDate.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(film.getData()));
//            etMessage.setText(film.getMessage());
//
//            ArrayAdapter<String> adaptor = (ArrayAdapter<String>)spinnerNoteType.getAdapter();
//            for(int i=0;i<adaptor.getCount();i++)
//                if(adaptor.getItem(i).equals(film.getNotetype()))
//                {
//                    spinnerNoteType.setSelection(i);
//                    break;
//                }
//            if(film.getCurNote().equals("Lecture"))
//                radioGroup.check(R.id.radioBtnLecture);
//            else
//            if(film.getCurNote().equals("Lab"))
//                radioGroup.check(R.id.radioBtnLab);
//            else
//                radioGroup.check(R.id.radioBtnOthers);
        }

        Button btnSalvare = (Button) findViewById(R.id.btnSalvare);
        btnSalvare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(etMovieTitle.getText().toString().isEmpty())
                {
                    etMovieTitle.setError("Introduceti titlul");
                    return;
                }
//                if (etDate.getText().toString().isEmpty())
//                {
//                    Toast.makeText(getApplicationContext(), "Introduceti data in jurnal", Toast.LENGTH_LONG).show();
//                    return;
//                }
//                if(etMessage.getText().toString().isEmpty())
//                {
//                    etMessage.setError("Scrieti notita");
//                    return;
//                }
//
//                //creare obiect clasa Film
//
//                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);

                //                    sdf.parse(etDate.getText().toString());

                String title = etMovieTitle.getText().toString();
//                    Date data = new Date(etDate.getText().toString());
//                    String mesaj = etMessage.getText().toString();
//                    NoteType type = NoteType.valueOf(spinnerNoteType.getSelectedItem().toString().toUpperCase().replace(" ", ""));
//
//                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
//                    CurricularNote crnote = CurricularNote.valueOf(radioButton.getText().toString().toUpperCase());
//                    Film notita = new Film(title, data, mesaj, type, crnote);
                Film film = new Film(title);

                intent.putExtra(ADD_MOVIE, film);
                setResult(RESULT_OK, intent);
                finish();

            }

        });
    }
}

