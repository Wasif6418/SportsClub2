package com.example.sportsclub.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sportsclub.Auth.LoginActivity;
import com.example.sportsclub.R;
import com.rey.material.widget.Button;

public class Sports extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        ViewGroup viewGroup= (ViewGroup) inflater.inflate(R.layout.fragment_sports, container, false);


        Button btn=(Button) viewGroup.findViewById(R.id.button_one);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
               Intent intent= new Intent(getActivity(), LoginActivity.class)  ;
               startActivity(intent);

            }
        });

        return viewGroup;
    }
}