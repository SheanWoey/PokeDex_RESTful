package com.example.sheanwoey_yifan.Pokemon_Detail.ui.pokeInfo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sheanwoey_yifan.R;

public class PokeInfoFragment extends Fragment {

    private PokeInfoViewModel pokeInfoViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pokeInfoViewModel =
                ViewModelProviders.of(this).get(PokeInfoViewModel.class);
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        pokeInfoViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.d("test",getActivity().getLocalClassName());
            }
        });
        return root;
    }
}