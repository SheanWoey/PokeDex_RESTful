package com.example.sheanwoey_yifan.Pokemon_Detail;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


public class PokeDetailActivity extends AppCompatActivity {

    private static final String TAG = PokeDetailActivity.class.getSimpleName();
    private PokeDetail pokeDetail;
    private int colorType;

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

        ConstraintLayout container = findViewById(R.id.container);

        colorType =colorChangeType();
        int[] colors = {Color.BLACK, Color.parseColor(getResources().getString(colorType)), Color.BLACK};

        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setColors(colors);

        container.setBackground(gd);
    }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed");
        if(getFragmentManager().getBackStackEntryCount() == 0) {
            super.onBackPressed();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public int colorChangeType() {
        switch (pokeDetail.getTypes()[0]) {
            case "grass":
                return R.color.colorGrassType;

            case "fire":
                return R.color.colorFireType;

            case "water":
                return R.color.colorWaterType;

            case "bug":
                return R.color.colorBugType;

            case "normal":
                return R.color.colorNormalType;

            case "poison":
                return R.color.colorPoisonType;

            case "electric":
                return R.color.colorElectricType;

            case "flying":
                return R.color.colorFlyingType;

            case "ground":
                return R.color.colorGroundType;

            case "fighting":
                return R.color.colorFightingType;

            case "psychic":
                return R.color.colorPsychicType;

            case "rock":
                return R.color.colorRockType;

            case "ghost":
                return R.color.colorGhostType;

            case "steel":
                return R.color.colorSteelType;

            case "ice":
                return R.color.colorIceType;

            case "dark":
                return R.color.colorDarkType;

            case "dragon":
                return R.color.colorDragonType;

            default:
                return R.color.Pink;
        }
    }
}
