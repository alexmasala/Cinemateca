package com.example.cinemaapp.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.fragment.app.Fragment;

import com.example.cinemaapp.R;

public class SettingsFragment extends Fragment {
    public SettingsFragment() {
        // Required empty public constructor
    }

    public static final String SAVE_SWITCH = "saveSwitch";
    public static final String IS_CHECKED = "isChecked";
    private Switch switchTheme;
    private SharedPreferences sharedPreferences;
    private boolean darkMode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        switchTheme = (Switch) view.findViewById(R.id.switchMode);
        sharedPreferences = getActivity().getApplicationContext().getSharedPreferences(SAVE_SWITCH, Context.MODE_PRIVATE);

        loadFromSharedPreferences();
        switchTheme.setChecked(sharedPreferences.getBoolean(IS_CHECKED, false));
        switchTheme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            //Scriere in fis SharedPreferences
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                darkMode = isChecked;
                storeToSharedPreferences();
            }
        });


        return view;
    }

    //Citire din fis SharedPreferences Dark/Light mode
    private void loadFromSharedPreferences() {
        darkMode = sharedPreferences.getBoolean(IS_CHECKED, false);
        updateInterface();
    }

    private void storeToSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_CHECKED, darkMode);
        editor.apply();
        editor.commit();
        updateInterface();
    }

    private void updateInterface() {
        MainActivity activity = ((MainActivity) getActivity());
        if (activity != null) {
            activity.toggleTheme(darkMode);
        }
    }
}