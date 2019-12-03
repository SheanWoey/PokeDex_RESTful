package com.example.sheanwoey_yifan.Pokemon_Detail.ui.pokeInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PokeInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PokeInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}