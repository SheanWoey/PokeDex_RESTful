package com.example.sheanwoey_yifan.Pokemon_Team;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheanwoey_yifan.AutoFitGridLayoutManager;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class PokemonSelectorActivity extends AppCompatActivity {

    private static final String TAG = PokemonSelectorActivity.class.getName();
    public static final String EXTRA_REPLY = "PokemonSelectorAdapter.REPLY";

    public RecyclerView pokemonList;
    public static ArrayList<PokeDetail> pokeLists = new ArrayList<>();
    private PokemonSelectorAdapter pokemonSelectorAdapter;
    private ProgressDialog pDialog;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_list_activity);
        pokemonList = findViewById(R.id.pokemonList);

        SharedPreferences user= getSharedPreferences("User", Context.MODE_PRIVATE);

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.hide();

        pokeLists = (ArrayList<PokeDetail>) getIntent().getSerializableExtra("team");

        pokemonList.setLayoutManager(new AutoFitGridLayoutManager(getApplicationContext(), 280));
        pokemonList.setHasFixedSize(true);
        pokemonSelectorAdapter = new PokemonSelectorAdapter(getApplicationContext(), pokeLists);
        pokemonList.setAdapter(pokemonSelectorAdapter);
        // do we need to notify the RecyclerView that the list has been updated?
        pokemonSelectorAdapter.notifyDataSetChanged();
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        pokemonSelectorAdapter.setClickListener(new PokemonSelectorAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent replyIntent = new Intent();
                replyIntent.putExtra(EXTRA_REPLY, pokeLists.get(position).getId());
                setResult(RESULT_OK, replyIntent);
                finish();
            }
        });
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

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

}
