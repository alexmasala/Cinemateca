package com.example.cinemaapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cinemaapp.R;


public class ContactUsActivity extends AppCompatActivity {
    private EditText eSubject;
    private EditText eMsg;
    private EditText eTomail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        Button btnTrimite = findViewById(R.id.btnTrimiteEmail);
        eSubject = findViewById(R.id.EditTextSubiectMail);
        eMsg = findViewById(R.id.EditTextMesajMail);
        eTomail = findViewById(R.id.EditTextToMail);
        btnTrimite.setOnClickListener(v -> {
            Intent it = new Intent(Intent.ACTION_SEND);
            it.putExtra(Intent.EXTRA_EMAIL, new String[]{eTomail.getText().toString()});
            it.putExtra(Intent.EXTRA_SUBJECT,eSubject.getText().toString());
            it.putExtra(Intent.EXTRA_TEXT,eMsg.getText());
            it.setType(getString(R.string.email_msg_type));
            startActivity(Intent.createChooser(it,getString(R.string.choose_app)));
        });
    }
}