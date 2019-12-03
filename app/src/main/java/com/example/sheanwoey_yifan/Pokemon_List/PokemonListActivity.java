package com.example.sheanwoey_yifan.Pokemon_List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.sheanwoey_yifan.Model.PokeList;
import com.example.sheanwoey_yifan.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PokemonListActivity extends AppCompatActivity {

    private static final String TAG = PokemonListActivity.class.getName();
    private static final String BASE_URL = "https://raw.githubusercontent.com/Biuni/PokemonGO-Pokedex/master/pokedex.json";

    public RecyclerView pokemonList;
    public PokemonListAdapter pokemonListAdapter;
    public static ArrayList<PokeList> pokeLists = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_list_activity);
        pokemonList = findViewById(R.id.pokemonList);
            apiRequest("");
    }

    private void apiRequest(String apiUrl) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(BASE_URL+apiUrl)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG,"Fail");
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String myResponse = response.body().string();

                    PokemonListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            JsonConvert(myResponse);
                        }
                    });
                }
            }
        });
    }

    public void JsonConvert(String myResponse) {
        try {
            PokeList pokeListItem;
            JSONObject responseObject = (JSONObject) new JSONTokener(myResponse).nextValue();
            JSONArray responseArray = responseObject.getJSONArray("pokemon");
            for (int i = 0; i < responseArray.length() ; i++) {
                String name = responseArray.getJSONObject(i).getString("name");
                String sprite = responseArray.getJSONObject(i).getString("img");
                JSONArray types = responseArray.getJSONObject(i).getJSONArray("type");
                if(types.length()==1) {
                    pokeListItem = new PokeList(name,sprite, new String[]{types.get(0).toString(), "none"});
                }else {
                    pokeListItem = new PokeList(name,sprite, new String[]{types.get(0).toString(), types.get(1).toString()});
                }
                pokeLists.add(pokeListItem);
            }
            initRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG,"pokeLists :"+ pokeLists.toString());
        pokemonList.setLayoutManager(new GridLayoutManager(this, 3));
        pokemonList.setHasFixedSize(true);
        pokemonListAdapter = new PokemonListAdapter(this, pokeLists);
        pokemonList.setAdapter(pokemonListAdapter);
    }

}
