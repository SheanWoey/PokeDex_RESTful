package com.example.sheanwoey_yifan.Pokemon_Detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class PokeDetailActivity extends AppCompatActivity {

    private static final String TAG = PokeDetailActivity.class.getSimpleName();
    private PokeDetail pokeDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_detail);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        pokeDetail = (PokeDetail) getIntent().getSerializableExtra("pokemon");

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_info, R.id.navigation_skills)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ImageView pokeIcon = findViewById(R.id.pokeIcon2);
        Glide.with(getApplicationContext()).load(pokeDetail.getSprite()).into(pokeIcon);

        TextView name = findViewById(R.id.pokeName);
        name.setText(pokeDetail.getName());
    }
}
