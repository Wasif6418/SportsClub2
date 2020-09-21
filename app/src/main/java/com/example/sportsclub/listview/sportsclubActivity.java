package com.example.sportsclub.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportsclub.R;

public class sportsclubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final int[] pos = {0};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportsclub);

        Intent intent = getIntent();
        pos[0] = intent.getExtras().getInt("Position");


        final  CustomAdapter adapter= new CustomAdapter(this);
        final ImageView img = (ImageView) findViewById(R.id.imageView2);
        final TextView name = (TextView) findViewById(R.id.name);
        final TextView price = (TextView) findViewById(R.id.price);

        //set date

        img.setImageResource(adapter.images[pos[0]]);
        name.setText(adapter.names[pos[0]]);
        price.setText(adapter.price[pos[0]]);

        Button btnnext = (Button) findViewById(R.id.button);

        final int[] finalPos = {pos[0]};
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int position = finalPos[0] +1 ;
                img.setImageResource(adapter.images[finalPos[0]]);
                name.setText("Name: "+ adapter.names[finalPos[0]]);
                price.setText("Price: "+ adapter.price[finalPos[0]]);

                if(!(position >= adapter.getCount()-1))
                {
                    finalPos[0] += 1;
                }
                else
                {
                    finalPos[0] = -1;
                }

            }
        });



    }
}