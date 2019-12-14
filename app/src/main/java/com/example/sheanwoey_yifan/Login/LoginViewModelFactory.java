package com.example.sheanwoey_yifan.Login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private LoggedInUser user;
    private Context context;


    public LoginViewModelFactory(Context context, LoggedInUser user)
    {
        this.context = context;
        this.user = user;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        return (T) new LoginViewModel(context, user);
    }
}
