package com.example.sportsclub;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class AdminCategoryActivity extends AppCompatActivity
{
    private ImageView bat,football,table_tennis,badminton,racket,basketball;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);


        bat = (ImageView)  findViewById(R.id.sports_bat);
        football = (ImageView) findViewById(R.id.sports_football);
        table_tennis = (ImageView) findViewById(R.id.sports_table_tennis);
        badminton = (ImageView) findViewById(R.id.sports_badminton);
        racket = (ImageView) findViewById(R.id.sports_racket);
        basketball= (ImageView) findViewById(R.id.sports_basketball);

  //Show the intent class and start the activity
        bat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "bat");
                startActivity(intent);
            }
        });

        football.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "football");
                startActivity(intent);
            }
        });


        table_tennis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "table_tennis");
                startActivity(intent);
            }
        });


        badminton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "badminton");
                startActivity(intent);
            }
        });


        racket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "racket");
                startActivity(intent);
            }
        });

        basketball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminAddNewProductActivity.class);
                intent.putExtra("category", "basketball");
                startActivity(intent);
            }
        });
    }
}