package com.example.sportsclub.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sportsclub.Fragments.Cart;
import com.example.sportsclub.Fragments.Sports;
import com.example.sportsclub.Fragments.category;
import com.example.sportsclub.Model.Products;
import com.example.sportsclub.Prevalent.Prevalent;
import com.example.sportsclub.R;
import com.example.sportsclub.SettinsActivity;
import com.example.sportsclub.Testpage;
import com.example.sportsclub.ViewHolder.ProductViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.security.cert.PKIXRevocationChecker;

public class Dashboard<adapter> extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawer;
    private DatabaseReference ProductsRef;
    private RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
  //  private Toast userNameTextView;
   // private ImageView profileImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ProductsRef = FirebaseDatabase.getInstance().getReference().child("products");


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Cart()).commit();
            navigationView.setCheckedItem(R.id.nav_cart);
        }


  //      userNameTextView.setText(Prevalent.currentonlineUser.getName());
        //     Picasso.get().load(Prevalent.currentonlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        recyclerView = findViewById(R.id.recycler_menu);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.nav_cart:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Cart()).commit();
                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
               // startActivity(new Intent(this,Testpage.class));
                break;

           /* case R.id.nav_orders:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Sports()).commit();
                //startActivity(new Intent(this,nav_orders.class));
                break;
          */

            case R.id.nav_categories:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new category()).commit();
                  Toast.makeText(this, "categories", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this,nav_cart.class));
                // startActivity(new Intent(this,Testpage.class));
                break;

            case R.id.nav_settings:
                Toast.makeText(this, "Setting", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, SettinsActivity.class));
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(this,nav_orders.class));
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsRef, Products.class)
                .build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter=
                new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options)
                {
                    @Override
                    protected void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i, @NonNull final Products model) {
                        productViewHolder.txtProductName.setText(model.getPname());
                        productViewHolder.txtProductDescription.setText(model.getDescription());
                        productViewHolder.txtProductDescription.setText("Price = " + model.getPrice() + "$");
                        //Toast.makeText(getApplicationContext(),"firebase fetched",Toast.LENGTH_LONG).show();
                        Picasso.get().load(model.getImage()).into(productViewHolder.imageView);
                    }

                    @NonNull
                    @Override
                    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_items_layout, parent, false);
                        ProductViewHolder holder = new ProductViewHolder(view);
                        return holder;
                    }

                };
        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}