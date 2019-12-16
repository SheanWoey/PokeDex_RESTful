package com.example.sheanwoey_yifan.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sheanwoey_yifan.Pokemon_List.PokemonListActivity;
import com.example.sheanwoey_yifan.R;

public class LoginActivity extends AppCompatActivity {

    private String username, password;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        View relativeLayout= findViewById(R.id.activity_main);
        Animation animation= AnimationUtils.loadAnimation(this,android.R.anim.fade_in);
        relativeLayout.startAnimation(animation);

         final LinearLayout loginForm = findViewById(R.id.loginForm) ;
         final EditText usernameEditText = findViewById(R.id.username);
         final EditText passwordEditText = findViewById(R.id.password);
         final Button loginButton = findViewById(R.id.login);
         Button toggleRegister = findViewById(R.id.toggleRegister);
         final ProgressBar loadingProgressBar = findViewById(R.id.loading);

         final LinearLayout registerForm = findViewById(R.id.registerForm) ;
         EditText userRegisterEditText = findViewById(R.id.userRegister);
         EditText passRegisterEditText = findViewById(R.id.passRegister);
         final Button registerButton = findViewById(R.id.register);
         Button toggleLogin = findViewById(R.id.toggleLogin);
         final ProgressBar loadingProgressBar2 = findViewById(R.id.loading2);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("User", MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        if (pref.getString("username","").isEmpty()|| pref.getString("password","").isEmpty()) {
            editor.putString("username", "user");
            editor.putString("password", "password");

            editor.apply();
            editor.commit();
        }

        usernameEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                username = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                loginButton.setEnabled(validate(s));
                password = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        userRegisterEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                username = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        passRegisterEditText.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                registerButton.setEnabled(validate(s));
                password = s.toString();
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                if(pref.getString("username", "").equals(username)&&(pref.getString("password", "").equals(password))) {
                    updateUiWithUser();
                    setResult(Activity.RESULT_OK);
                 } else {
                    showLoginFailed("Invalid User");
                    loadingProgressBar.setVisibility(View.INVISIBLE);
                    loadingProgressBar2.setVisibility(View.INVISIBLE);
                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar2.setVisibility(View.VISIBLE);
                SharedPreferences.Editor editor = pref.edit();

                editor.putString("username", username);
                editor.putString("password", password);

                editor.apply();
                editor.commit();

                updateUiWithUser();
                setResult(Activity.RESULT_OK);
            }
        });


        toggleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginForm.setVisibility(View.VISIBLE);
                registerForm.setVisibility(View.GONE);
            }
        });

        toggleRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForm.setVisibility(View.VISIBLE);
                loginForm.setVisibility(View.GONE);

            }
        });
}

    private void updateUiWithUser() {
        String welcome = getString(R.string.welcome) +" "+ username;
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();

        Intent intent = new Intent(LoginActivity.this, PokemonListActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    private boolean validate(CharSequence s) {
            if(s.length()<=5) {
                return false;
            }
            else {
                return true;
            }
    }

    private void showLoginFailed(String errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}

