package com.example.sheanwoey_yifan.Pokemon_Detail.pokeInfo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.R;

public class PokeInfoFragment extends Fragment {

    private PokeDetail pokeDetail;
    public final static String TAG = PokeInfoFragment.class.getSimpleName();

    public PokeInfoFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pokeDetail =  (PokeDetail) getActivity().getIntent().getSerializableExtra("pokemon");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_info, container, false);

        TextView desc = view.findViewById(R.id.description);
        desc.setText(pokeDetail.getDescription());

        TextView valueHp,valueAttack,valueDefense, valueSpecialAttack, valueSpecialDefense, valueSpeed;
        valueHp = view.findViewById(R.id.valueHp);
        valueAttack = view.findViewById(R.id.valueAttack);
        valueDefense = view.findViewById(R.id.valueDefense);
        valueSpecialAttack = view.findViewById(R.id.valueSpecialAttack);
        valueSpecialDefense = view.findViewById(R.id.valueSpecialDefense);
        valueSpeed = view.findViewById(R.id.valueSpeed);

        valueHp.setText(String.valueOf(pokeDetail.getBaseHp()));
        valueAttack.setText(String.valueOf(pokeDetail.getBaseAtk()));
        valueDefense.setText(String.valueOf(pokeDetail.getBaseDef()));
        valueSpecialAttack.setText(String.valueOf(pokeDetail.getBaseSpAtk()));
        valueSpecialDefense.setText(String.valueOf(pokeDetail.getBaseSpDef()));
        valueSpeed.setText(String.valueOf(pokeDetail.getBaseSpd()));

        ProgressBar barHp, barAttack, barDefense, barSpecialAttack, barSpecialDefense, barSpeed;
        barHp = view.findViewById(R.id.barHp);
        barAttack = view.findViewById(R.id.barAttack);
        barDefense = view.findViewById(R.id.barDefense);
        barSpecialAttack = view.findViewById(R.id.barSpecialAttack);
        barSpecialDefense = view.findViewById(R.id.barSpecialDefense);
        barSpeed = view.findViewById(R.id.barSpeed);

        barHp.setProgress(pokeDetail.getBaseHp());
        barAttack.setProgress(pokeDetail.getBaseAtk());
        barDefense.setProgress(pokeDetail.getBaseDef());
        barSpecialAttack.setProgress(pokeDetail.getBaseSpAtk());
        barSpecialDefense.setProgress(pokeDetail.getBaseSpDef());
        barSpeed.setProgress(pokeDetail.getBaseSpd());

        int evolution = pokeDetail.getPokeEvolutions().size();

        int[][] ids = new int[][]{
                { R.id.pokemon_evolution1_name, R.id.pokemon_evolution1 },
                { R.id.pokemon_evolution2_name, R.id.pokemon_evolution2 },
                { R.id.pokemon_evolution3_name, R.id.pokemon_evolution3 }
        };

        if (evolution>0) {
            for (int i = 0; i < evolution; i++) {
                TextView evloveName = view.findViewById(ids[i][0]);
                evloveName.setText(pokeDetail.getPokeEvolutions().get(i).getName());
                ImageView evoleImg = view.findViewById(ids[i][1]);
                Glide.with(view).load(pokeDetail.getPokeEvolutions().get(i).getIcon()).into(evoleImg);

                evloveName.setVisibility(View.VISIBLE);
                evoleImg.setVisibility(View.VISIBLE);
            }
        }

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }


}
