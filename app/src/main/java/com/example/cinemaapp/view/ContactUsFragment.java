package com.example.cinemaapp.view;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.cinemaapp.R;

public class ContactUsFragment extends Fragment {

    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_about, container, false);

                Intent rateInt = new Intent(getActivity(), ContactUsActivity.class);
                startActivity(rateInt);



        return view;
    }
}
