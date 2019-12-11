package com.example.sheanwoey_yifan.Pokemon_Detail.PokeSkills;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PokeSkillsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PokeSkillsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}