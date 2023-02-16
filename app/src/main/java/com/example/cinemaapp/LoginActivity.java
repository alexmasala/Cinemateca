package com.example.cinemaapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cinemaapp.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

public class LoginActivity extends AppCompatActivity {

    private EditText email, passw;
    private Button login;
    private TextView newHere;
    private FirebaseAuth authFireBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = findViewById(R.id.btnLogIn);
        newHere = findViewById(R.id.newHere);
        email = findViewById(R.id.email3);
        passw = findViewById(R.id.password3);
        authFireBase = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            //Preluare date existente din firebase
            @Override
            public void onClick(View v) {
                loginUserAccount();
            }
        });

        newHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    //Functia de autentificare utilizator
    private void loginUserAccount() {

        String emailEt = email.getText().toString().trim();
        String passwordEt = passw.getText().toString().trim();

        //Validare email, parola
        if (TextUtils.isEmpty(emailEt)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter email!!",
                    Toast.LENGTH_LONG)
                    .show();
        }

        if (TextUtils.isEmpty(passwordEt)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!!",
                    Toast.LENGTH_LONG)
                    .show();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailEt).matches()){
            Toast.makeText(this, "Provide a valid email",
                    Toast.LENGTH_SHORT).show();
        }

        if(passwordEt.length() < 6)
            Toast.makeText(this, "Password should be at least 6 characters!",
                    Toast.LENGTH_SHORT).show();

        authFireBase.signInWithEmailAndPassword(emailEt, passwordEt)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>(){

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }else{

                            Toast.makeText(LoginActivity.this,
                                    "Failed to login! Please check your credentials!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}