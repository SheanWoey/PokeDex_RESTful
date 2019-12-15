package com.example.sheanwoey_yifan.Pokemon_Detail.PokeSprite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.R;

public class pokeSpriteFragment extends Fragment {

    public pokeSpriteFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_poke_sprite, container, false);

        ImageView pokeSprite = view.findViewById(R.id.pokeSprite);
        PokeDetail pokeDetail =  (PokeDetail) getActivity().getIntent().getSerializableExtra("pokemon");
        Glide.with(view).load(pokeDetail.getSprite()).into(pokeSprite);

        TextView textView = view.findViewById(R.id.pokeName);
        ImageView imageView = view.findViewById(R.id.pokeIcon2);

        Glide.with(view).load(pokeDetail.getSprite()).into()

        return view;
    }

}
