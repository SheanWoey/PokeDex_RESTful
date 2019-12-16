package com.example.sheanwoey_yifan;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sheanwoey_yifan.Login.LoginActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private boolean isFirstAnimation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_screen);

        final Animation rotate = AnimationUtils.loadAnimation(this, R.anim.rotate);
        final Animation fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        final ImageView splash_content = findViewById(R.id.header_icon);
        final TextView textView = findViewById(R.id.welcomeText);


        rotate.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (!isFirstAnimation) {
                    splash_content.startAnimation(fade_out);
                    textView.startAnimation(fade_out);
                }

                isFirstAnimation = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        fade_out.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                splash_content.startAnimation(fade_out);
                textView.startAnimation(fade_out);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                splash_content.setVisibility(View.GONE);
                textView.setVisibility(View.GONE);
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        splash_content.startAnimation(rotate);
    }
}
