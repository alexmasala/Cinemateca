package com.example.cinemaapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.cinemaapp.view.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText password, email, faculty, specialization, firstName, lastName;
    private Button signUp;
    private TextView existingUser;
    private ProgressBar progbar;
  //  private FirebaseDatabase firebaseUser;
     private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signUp = findViewById(R.id.btnSignUp);
        existingUser = findViewById(R.id.AlreadyRegistered);
        password = findViewById(R.id.Password);
        email = findViewById(R.id.Email);
        faculty = findViewById(R.id.faculty);
        specialization = findViewById(R.id.specialization);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        progbar =  findViewById(R.id.progressBar);
        mAuth = FirebaseAuth.getInstance();
        //Firebase tabela user
        //Creare si inserare user Adaugare user in firebase
        //Functie de salvare in FireBase

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        existingUser.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivityMADA.class));
            }
        });
    }

    private void registerUser(){
        String emailEt = email.getText().toString().trim();
        String passwordEt = password.getText().toString().trim();
        String facultyEt = faculty.getText().toString().trim();
        String specializtionEt = specialization.getText().toString().trim();
        String firstNameEt = firstName.getText().toString().trim();
        String lastNameEt = lastName.getText().toString().trim();

        if(firstNameEt.isEmpty()) {
//            Toast.makeText(this, "This field is required!",
//                    Toast.LENGTH_SHORT).show();
            firstName.setError("First name is required.");
            return;
        }
        if(lastNameEt.isEmpty()) {
//            Toast.makeText(this, "This field is required!",
//                    Toast.LENGTH_SHORT).show();
            lastName.setError("Last name is required.");
            return;
        }
        if(passwordEt.isEmpty()) {
//            Toast.makeText(this, "This field is required!",
//                    Toast.LENGTH_SHORT).show();
            password.setError("A password is required.");
            return;
        }
        if(facultyEt.isEmpty()) {
//            Toast.makeText(this, "This field is required!",
//                    Toast.LENGTH_SHORT).show();
            faculty.setError("Faculty is required.");
            return;
        }
        if(specializtionEt.isEmpty()) {
//            Toast.makeText(this, "This field is required!",
//                    Toast.LENGTH_SHORT).show();
            specialization.setError("Specialization is required.");
            return;
        }
        if(emailEt.isEmpty()) {
//            Toast.makeText(this, "This field is required!",
//                    Toast.LENGTH_SHORT).show();
            email.setError("An email is required.");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailEt).matches()) {
//            Toast.makeText(this, "Provide a valid email",
//                    Toast.LENGTH_SHORT).show();
            email.setError("Provide a valid email");
            return;
        }
        if(passwordEt.length() < 6) {
//            Toast.makeText(this, "Password should be at least 6 characters!",
//                    Toast.LENGTH_SHORT).show();
            password.setError("Password should be at least 6 characters.");
            return;
        }
        progbar.setVisibility(View.VISIBLE);
        //Verifica daca userul este deja inregistrat
        mAuth.createUserWithEmailAndPassword(emailEt, passwordEt)
                .addOnCompleteListener(com.example.cinemaapp.RegisterActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
              if(task.isSuccessful()){
                  User user = new User(lastNameEt, firstNameEt, facultyEt, specializtionEt, emailEt, passwordEt);

                  FirebaseDatabase.getInstance().getReference("Users")
                          .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                          .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if(task.isSuccessful()){
                              Toast.makeText(com.example.cinemaapp.RegisterActivity.this,
                                       "User has been registered successfully!", Toast.LENGTH_SHORT).show();
                              progbar.setVisibility(View.GONE);
                              ////////////////////////////
                              startActivity(new Intent(getApplicationContext(), LoginActivity.class));
//                              finish();
                          } else{
                              Toast.makeText(com.example.cinemaapp.RegisterActivity.this, "Failed to register!", Toast.LENGTH_SHORT).show();
                              progbar.setVisibility(View.GONE);
                          }
                      }
                  });
              } else{
                  Toast.makeText(com.example.cinemaapp.RegisterActivity.this, "Registering was unsuccessful!", Toast.LENGTH_SHORT).show();
                  progbar.setVisibility(View.GONE);
              }
            }
        });
    }
}