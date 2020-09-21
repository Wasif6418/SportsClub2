package com.example.sportsclub.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportsclub.R;

public class CustomAdapter extends BaseAdapter
   {
       Context c;
       String[] names= {"Bat", "Football","Basketball"};
       String[] price = {"1000", "800", "1200"};
       int[] images={R.drawable.bat, R.drawable.football, R.drawable.basketball};

       public CustomAdapter (Context ctx)
       {
           this.c= ctx;
       }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return names[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sports,null);

        }
        //get view
        TextView nametxt= (TextView) convertView.findViewById(R.id.name);
        TextView pricetxt= (TextView) convertView.findViewById(R.id.price);
        //ImageView img= (ImageView) convertView.findViewById(R.id.imageView2);

        //set date
        nametxt.setText(names[position]);
        pricetxt.setText(price[position]);
        //img.setImageResource(images[position]);


        return convertView;
    }
}
