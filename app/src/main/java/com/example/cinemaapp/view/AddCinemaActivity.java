package com.example.cinemaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;
import com.example.cinemaapp.model.Cinema;
import com.example.cinemaapp.model.Film;

public class AddCinemaActivity extends AppCompatActivity {
    public static final String ADD_MOVIE = "addMovie";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cinema);



        final Spinner spinnerOras = findViewById(R.id.spinnerOras);

        //Leg Spinner-Enum
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.add_oras, R.layout.support_simple_spinner_dropdown_item);
        spinnerOras.setAdapter(adapter);

        final EditText etCinemaTitle = findViewById(R.id.editCinemaTitle);
//        final EditText etDate = findViewById(R.id.editTextDate);
        final EditText etSali = findViewById(R.id.etNrSali);
//        final String DATE_FORMAT = "MM/dd/yyyy";
//
//        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final Intent intent = getIntent();

        if(intent.hasExtra(AddCinemaFragment.EDIT_MOVIE)){

            Cinema cinema = (Cinema) intent.getSerializableExtra(AddCinemaFragment.EDIT_MOVIE);

            etCinemaTitle.setText(cinema.getNameCinema());
            etCinemaTitle.setText(cinema.getNrSali());
//            etDate.setText(new SimpleDateFormat(DATE_FORMAT, Locale.US).format(film.getData()));
//            etMessage.setText(film.getMessage());
//
            ArrayAdapter<String> adaptor = (ArrayAdapter<String>)spinnerOras.getAdapter();
            for(int i=0;i<adaptor.getCount();i++)
                if(adaptor.getItem(i).equals(cinema.getOras()))
                {
                    spinnerOras.setSelection(i);
                    break;
                }
        }

        Button btnSalvare = (Button) findViewById(R.id.btnSalvareCinema);
        btnSalvare.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(etCinemaTitle.getText().toString().isEmpty())
                {
                    etCinemaTitle.setError("Introduceti titlul");
                    return;
                }
//                if (etDate.getText().toString().isEmpty())
//                {
//                    Toast.makeText(getApplicationContext(), "Introduceti data in jurnal", Toast.LENGTH_LONG).show();
//                    return;
//                }
                if(etSali.getText().toString().isEmpty())
                {
                    etSali.setError("Introudceti nr. de sali");
                    return;
                }
//
//                //creare obiect clasa Film
//
//                SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.US);

                //                    sdf.parse(etDate.getText().toString());

                String title = etCinemaTitle.getText().toString();
//                    Date data = new Date(etDate.getText().toString());
                    String sali = etSali.getText().toString();
                    Cinema.Oras oras = Cinema.Oras.valueOf(spinnerOras.getSelectedItem().toString().toUpperCase().replace(" ", ""));
//
//                    RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
//                    CurricularNote crnote = CurricularNote.valueOf(radioButton.getText().toString().toUpperCase());
//                    Film notita = new Film(title, data, mesaj, type, crnote);
                Cinema cinema = new Cinema(title, sali, oras);

                intent.putExtra(ADD_MOVIE, cinema);
                setResult(RESULT_OK, intent);
                finish();

            }

        });
    }
}
