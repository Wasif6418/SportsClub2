package com.example.sportsclub.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sportsclub.Interface.ItemClickListner;
import com.example.sportsclub.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{

    public TextView txtProductName, txtProductDescription, txtProductPrice;
    public ImageView imageView;
    public ItemClickListner listner;

    public ProductViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageView =    (ImageView)  itemView.findViewById(R.id.product_image);
        txtProductName =    (TextView)  itemView.findViewById(R.id.product_name);
        txtProductDescription =    (TextView)  itemView.findViewById(R.id.product_description);
        txtProductPrice = (TextView)  itemView.findViewById(R.id.product_price);

    }

    public void setItemClickListner(ItemClickListner listner)
    {
        this.listner = listner;
    }

    @Override
    public void onClick(View v)
    {
        listner.onClick(v, getAdapterPosition(), false);

    }



}
