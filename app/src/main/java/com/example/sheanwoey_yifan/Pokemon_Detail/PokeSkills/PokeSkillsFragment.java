package com.example.sheanwoey_yifan.Pokemon_Detail.PokeSkills;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.Pokemon_Detail.PokeDetailActivity;
import com.example.sheanwoey_yifan.Pokemon_List.PokemonListActivity;
import com.example.sheanwoey_yifan.R;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class PokeSkillsFragment extends Fragment {

    private static final String TAG = PokemonListActivity.class.getName();

    public PokeSkillsAdapter pokeSkillsAdapter;
    public RecyclerView pokeSkills;
    public static PokeDetail pokeDetail = new PokeDetail();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pokeDetail = (PokeDetail) getActivity().getIntent().getSerializableExtra("pokemon");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_skill, container, false);
        pokeSkills = view.findViewById(R.id.pokeMoves);

        pokeSkills.setLayoutManager(new GridLayoutManager(getContext(), 1));
        pokeSkills.setHasFixedSize(true);
        pokeSkillsAdapter = new PokeSkillsAdapter(getActivity(), pokeDetail.getPokeMoves());
        pokeSkills.setAdapter(pokeSkillsAdapter);
        // do we need to notify the RecyclerView that the list has been updated?
        pokeSkillsAdapter.notifyDataSetChanged();

        return view;
    }

}