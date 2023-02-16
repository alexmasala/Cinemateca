package com.example.cinemaapp.view;

import android.content.Intent;
import android.os.Bundle;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.cinemaapp.LoginActivityMADA;
import com.example.cinemaapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Fragment selectedFragment;
    FirebaseAuth auth;
    FloatingActionButton floatingSignOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.AppThemeDark);
        } else {
            setTheme(R.style.AppTheme);
        }
        floatingSignOut = findViewById(R.id.floatingSignOut);
        auth = FirebaseAuth.getInstance();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //keep selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }

        floatingSignOut.setOnClickListener(v -> {
            if (auth.getCurrentUser() != null) {
                auth.signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivityMADA.class);
                startActivity(intent);
            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.navigation_reservation:
                    selectedFragment = new ReservationFragment();
                    break;
                case R.id.navigation_favorites:
                    selectedFragment = new FavoritesFragment();
                    break;
//                case R.id.navigation_add_film:
//                    selectedFragment = new AddMovieFragment();
//                    break;
                case R.id.navigation_add_cinema:
                    selectedFragment = new AddCinemaFragment();
                    break;
//                case R.id.navigation_settings:
//                    selectedFragment = new SettingsFragment();
//                    break;
                 case R.id.navigation_about_app:
                    selectedFragment = new AboutFragment();
                    break;
//                case R.id.navigation_contacts_us:
//                    selectedFragment = new ContactUsFragment();
//                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected  void onStart() {
        super.onStart();
    }
    //AppCompatDelegate.setDefaultNightMode will cause your activities to reload automatically
    public void toggleTheme(boolean isChecked ){
        if (isChecked) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
