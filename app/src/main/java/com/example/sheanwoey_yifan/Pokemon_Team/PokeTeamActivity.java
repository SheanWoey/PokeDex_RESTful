package com.example.sheanwoey_yifan.Pokemon_Team;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sheanwoey_yifan.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PokeTeamActivity extends AppCompatActivity {

    public static String TAG =  PokeTeamActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;

    public ConstraintLayout team1, team2, team3, team4;
    public TextView team1Name, team1Type1, team1Type2;
    public ImageView team1Icon;

    public TextView team2Name, team2Type1, team2Type2;
    public ImageView team2Icon;

    public TextView team3Name, team3Type1, team3Type2;
    public ImageView team3Icon;

    public TextView team4Name, team4Type1, team4Type2;
    public ImageView team4Icon;

    public List<TextView> TeamName, TeamType1, TeamType2;
    public List<ImageView> TeamIcon;
    public static ArrayList<PokeDetail> pokeLists;
    private SharedPreferences sharedPreferences;

    private int teamModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_poke_team);
        sharedPreferences = getSharedPreferences("Team", MODE_PRIVATE);
        pokeLists = (ArrayList<PokeDetail>) getIntent().getSerializableExtra("team");

        team1 = findViewById(R.id.team1);
        team2 = findViewById(R.id.team2);
        team3 = findViewById(R.id.team3);
        team4 = findViewById(R.id.team4);

        team1Name = findViewById(R.id.pokeNameTeam1);
        team1Type1 = findViewById(R.id.pokeType1Team1);
        team1Type2 = findViewById(R.id.pokeType2Team1);
        team1Icon = findViewById(R.id.imageView1);

        team2Name = findViewById(R.id.pokeNameTeam2);
        team2Type1 = findViewById(R.id.pokeType1Team2);
        team2Type2 = findViewById(R.id.pokeType2Team2);
        team2Icon = findViewById(R.id.imageView2);

        team3Name = findViewById(R.id.pokeNameTeam3);
        team3Type1 = findViewById(R.id.pokeType1Team3);
        team3Type2 = findViewById(R.id.pokeType2Team3);
        team3Icon = findViewById(R.id.imageView3);

        team4Name = findViewById(R.id.pokeNameTeam4);
        team4Type1 = findViewById(R.id.pokeType1Team4);
        team4Type2 = findViewById(R.id.pokeType2Team4);
        team4Icon = findViewById(R.id.imageView4);

        TeamName = Arrays.asList(team1Name, team2Name, team3Name, team4Name);
        TeamType1 = Arrays.asList(team1Type1, team2Type1, team3Type1, team4Type1);
        TeamType2 = Arrays.asList(team1Type2, team2Type2, team3Type2, team4Type2);
        TeamIcon = Arrays.asList(team1Icon, team2Icon, team3Icon, team4Icon);

        for (int i = 0; i < TeamName.size(); i++) {
            teamModify(i);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        team1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PokeTeamActivity.this, PokemonSelectorActivity.class);
                intent.putExtra("team", (Serializable) pokeLists);
                startActivityForResult(intent, TEXT_REQUEST);

                teamModify =1 ;
                teamModify(1);
            }
        });

        team2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(PokeTeamActivity.this, PokemonSelectorActivity.class);
                intent.putExtra("team", (Serializable) pokeLists);
                startActivityForResult(intent, TEXT_REQUEST);

                teamModify =2 ;
                teamModify(2);
            }
        });

        team3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PokeTeamActivity.this, PokemonSelectorActivity.class);
                intent.putExtra("team", (Serializable) pokeLists);
                startActivityForResult(intent, TEXT_REQUEST);

                teamModify=3;
                teamModify(3);
            }
        });

        team4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PokeTeamActivity.this, PokemonSelectorActivity.class);
                startActivityForResult(intent, TEXT_REQUEST);

                teamModify=4;
                teamModify(4);

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            int reply = data.getIntExtra(PokemonSelectorActivity.EXTRA_REPLY, 0);
            sharedPreferences.getInt("Team"+teamModify, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("Team"+teamModify,reply);
            editor.apply();
            editor.commit();

            teamModify(teamModify);
        }
    }

    public void teamModify(int team) {
        int position = sharedPreferences.getInt("Team"+team, 0);
        Log.d(TAG, "position "+position);
        if(position !=0 ) {
            team--;
            position--;
            TeamName.get(team).setText(pokeLists.get(position).getName());
            TeamType1.get(team).setText(pokeLists.get(position).getTypes()[0]);
            if (pokeLists.get(position).getTypes().length != 1) {
                TeamType2.get(team).setText(pokeLists.get(position).getTypes()[1]);
            } else {
                TeamType2.get(team).setVisibility(View.INVISIBLE);
            }
            Glide.with(getApplicationContext()).load(pokeLists.get(position).getSprite()).into(TeamIcon.get(team));
        }
        else {
            Log.d(TAG,"Not FOund");
        }
    }
}
