package com.example.sheanwoey_yifan.Pokemon_List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.sheanwoey_yifan.AutoFitGridLayoutManager;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.Model.PokeEvolution;
import com.example.sheanwoey_yifan.Model.PokeMove;
import com.example.sheanwoey_yifan.Pokemon_Team.PokeTeamActivity;
import com.example.sheanwoey_yifan.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class PokemonListActivity extends AppCompatActivity {

    private static final String TAG = PokemonListActivity.class.getName();
    private static String BASE_URL = "https://demo4987693.mockable.io/pokemon";

    public RecyclerView pokemonList;
    public static ArrayList<PokeDetail> pokeLists = new ArrayList<>();
    private PokemonListAdapter pokemonListAdapter;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_list_activity);
        pokemonList = findViewById(R.id.pokemonList);

        SharedPreferences sharedpreferences= getSharedPreferences("Base_Url", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("PokeList_Url","http://demo4987693.mockable.io/pokemon");
        editor.apply();
        editor.commit();

        SharedPreferences user= getSharedPreferences("User", Context.MODE_PRIVATE);
        Log.d(TAG,"user: "+user.getString("username","")+" password: "+ user.getString("password",""));

        new GetPokeList().execute();


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PokemonListActivity.this, PokeTeamActivity.class);
                intent.putExtra("team", (Serializable) pokeLists);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }


    private class GetPokeList extends AsyncTask<Void, Void, ArrayList<PokeDetail>> {

        ArrayList<PokeDetail> list;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(PokemonListActivity.this);
            pDialog.setIndeterminate(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }


        @Override
        protected ArrayList<PokeDetail> doInBackground(Void... arg0) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(BASE_URL)
                    .build();

            try {
                String myResponse = client.newCall(request).execute().body().string();
                list = JsonConvert(myResponse);
            } catch (IOException e) {
                Log.d(TAG, "Fail" + e.getMessage());
                Toast.makeText(getApplicationContext(),
                        "Json parsing error: " + e.getMessage(),
                        Toast.LENGTH_LONG)
                        .show();
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<PokeDetail> result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            pokeLists = result;
            Log.d(TAG, "pokeLists :" + pokeLists.size());

            pokemonList.setLayoutManager(new AutoFitGridLayoutManager(getApplicationContext(), 300));
            pokemonList.setHasFixedSize(true);
            pokemonListAdapter = new PokemonListAdapter(getApplicationContext(), pokeLists);
            pokemonList.setAdapter(pokemonListAdapter);
            // do we need to notify the RecyclerView that the list has been updated?
            pokemonListAdapter.notifyDataSetChanged();

        }
    }

    public ArrayList<PokeDetail> JsonConvert(String myResponse) {

        ArrayList<PokeDetail> list = new ArrayList<>();
        try {
            PokeDetail pokeDetail;
            JSONObject responseObject = (JSONObject) new JSONTokener(myResponse).nextValue();
            JSONArray responseArray = responseObject.getJSONArray("pokemon");
            for (int i = 0; i < responseArray.length(); i++) {
                int id = responseArray.getJSONObject(i).getInt("id");
                String name = responseArray.getJSONObject(i).getString("name");

                JSONArray typesArray = responseArray.getJSONObject(i).getJSONArray("types");
                String[] types;
                if (typesArray.length() == 1) {
                    types= new String[]{typesArray.get(0).toString(), "none"};
                } else {
                    types =  new String[]{typesArray.get(1).toString(), typesArray.get(0).toString()};
                }

                String sprite = responseArray.getJSONObject(i).getString("sprite");
                String description = responseArray.getJSONObject(i).getString("description");
                int hp = responseArray.getJSONObject(i).getInt("hp");
                int attack = responseArray.getJSONObject(i).getInt("attack");
                int defense = responseArray.getJSONObject(i).getInt("defense");
                int special_attack = responseArray.getJSONObject(i).getInt("special_attack");
                int special_defense = responseArray.getJSONObject(i).getInt("special_defense");
                int speed = responseArray.getJSONObject(i).getInt("speed");

                ArrayList<PokeMove> pokeMoveList = new ArrayList<>();
                JSONArray movesArray = responseArray.getJSONObject(i).getJSONArray("moves");
                for (int j = 0; j < movesArray.length(); j++) {
                    String moveName = movesArray.getJSONObject(j).getString("name");
                    String accuracy = movesArray.getJSONObject(j).getString("accuracy");
                    String power = movesArray.getJSONObject(j).getString("power");
                    String pp = movesArray.getJSONObject(j).getString("pp");
                    String damageClass = movesArray.getJSONObject(j).getString("category");
                    String element = movesArray.getJSONObject(j).getString("type");
                    String lvl = movesArray.getJSONObject(j).getString("method");
                    PokeMove pokeMove = new PokeMove(moveName,pp,accuracy,damageClass,element,power,lvl);
                    pokeMoveList.add(pokeMove);
                }
                Collections.sort(pokeMoveList, new PokeMove.SortbyLv());
                ArrayList<PokeEvolution> pokeEvolutions = new ArrayList<>();
                JSONArray evolutionArray = responseArray.getJSONObject(i).getJSONArray("evolutions");
                for (int j = 0; j < evolutionArray.length(); j++) {
                    int id2 = evolutionArray.getJSONObject(j).getInt("id");
                    String name2 = evolutionArray.getJSONObject(j).getString("name");
                    String icon = "https://www.serebii.net/pokemon/art/"+String.format("%03d", id2)+".png";
                    PokeEvolution pokeMove = new PokeEvolution(id2,name2,icon);
                    pokeEvolutions.add(pokeMove);
                }
                pokeDetail = new PokeDetail(id,name,types,sprite,description,pokeMoveList,pokeEvolutions,hp,defense,attack,speed,special_attack,special_defense);
                list.add(pokeDetail);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
