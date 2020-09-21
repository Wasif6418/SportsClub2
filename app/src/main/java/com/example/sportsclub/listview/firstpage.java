package com.example.sportsclub.listview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.sportsclub.R;

import androidx.appcompat.app.AppCompatActivity;

public class firstpage extends AppCompatActivity {
    ListView listView;

    @Override

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_first);

        listView = (ListView) findViewById(R.id.listview);
        CustomAdapter adapter = new CustomAdapter(this);

        listView.setAdapter(adapter);
         listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id)
             {
                 Intent intent = new Intent(getApplicationContext(),sportsclubActivity.class);
                 intent.putExtra("Position", position);
                 startActivity(intent);


             }
         });
    }
}
