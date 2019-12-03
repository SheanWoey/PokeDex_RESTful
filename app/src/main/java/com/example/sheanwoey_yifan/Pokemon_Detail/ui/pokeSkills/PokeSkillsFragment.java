package com.example.sheanwoey_yifan.Pokemon_Detail.ui.pokeSkills;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sheanwoey_yifan.R;

public class PokeSkillsFragment extends Fragment {

    private PokeSkillsViewModel pokeSkillsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pokeSkillsViewModel =
                ViewModelProviders.of(this).get(PokeSkillsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_skill, container, false);
        pokeSkillsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d("test",getActivity().getLocalClassName());
            }
        });
        return root;
    }
}