package com.example.sheanwoey_yifan.Pokemon_Detail;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sheanwoey_yifan.Model.PokeDetail;
import com.example.sheanwoey_yifan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;


public class PokeDetailActivity extends AppCompatActivity {

    private static final String TAG = PokeDetailActivity.class.getSimpleName();
    private PokeDetail pokeDetail;
    private int colorType;
    private ProgressDialog pDialog;
    public OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poke_detail);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        pokeDetail = (PokeDetail) getIntent().getSerializableExtra("pokemon");

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_info, R.id.navigation_skills)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ImageView pokeIcon = findViewById(R.id.pokeIcon2);
        Glide.with(getApplicationContext()).load(pokeDetail.getSprite()).into(pokeIcon);

        ConstraintLayout container = findViewById(R.id.container);

        colorType =colorChangeType();
        int[] colors = {Color.BLACK, Color.parseColor(getResources().getString(colorType)), Color.BLACK};

        GradientDrawable gd = new GradientDrawable();
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setColors(colors);

        container.setBackground(gd);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        Button button = findViewById(R.id.download);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageDownloader().execute();
            }
        });

    }
        private class ImageDownloader extends AsyncTask<Integer, Integer, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                // Showing progress dialog
                pDialog = new ProgressDialog(PokeDetailActivity.this);
                pDialog.setIndeterminate(false);
                pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                pDialog.setMessage("Downloading image...");
                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected String doInBackground(Integer... params) {

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(pokeDetail.getSprite())
                            .build();

                    try {
                        InputStream myResponse = client.newCall(request).execute().body().byteStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(myResponse);

                        File mediaFile = Environment.getExternalStorageDirectory();
                        File dir = new File(mediaFile.getAbsolutePath() + "/AppImage");
                        dir.mkdir();
                        File downloadImg = new File(dir, System.currentTimeMillis() + ".png");

                        try {
                            outputStream = new FileOutputStream(downloadImg);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                        try {
                            outputStream.flush();
                            return "Image Downloaded";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } catch (Exception e) {
                        Log.d("ImageDownloader", "Something went wrong while" +
                                " retrieving bitmap from " + pokeDetail.getSprite() + e.toString());
                        Toast.makeText(getApplicationContext(), "Something went wrong while" +
                                " retrieving bitmap from " + pokeDetail.getSprite() + e.toString(), Toast.LENGTH_LONG).show();
                    }

                    return null;
                }

            @Override
            protected void onPostExecute(String result) {

                Log.d(TAG,result);

                if (pDialog.isShowing())
                    pDialog.dismiss();

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "CHANNEL_ID");
                builder.setContentTitle("Picture Download")
                        .setContentText("Download Completed")
                        .setSmallIcon(R.drawable.loading)
                        .setPriority(NotificationCompat.PRIORITY_LOW)
                        .build();

                NotificationManager notificationManager=  (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());

            }

        }

    @Override
    public void onBackPressed() {
        Log.d(TAG,"onBackPressed");
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public int colorChangeType() {
        switch (pokeDetail.getTypes()[0]) {
            case "grass":
                return R.color.colorGrassType;

            case "fire":
                return R.color.colorFireType;

            case "water":
                return R.color.colorWaterType;

            case "bug":
                return R.color.colorBugType;

            case "normal":
                return R.color.colorNormalType;

            case "poison":
                return R.color.colorPoisonType;

            case "electric":
                return R.color.colorElectricType;

            case "flying":
                return R.color.colorFlyingType;

            case "ground":
                return R.color.colorGroundType;

            case "fighting":
                return R.color.colorFightingType;

            case "psychic":
                return R.color.colorPsychicType;

            case "rock":
                return R.color.colorRockType;

            case "ghost":
                return R.color.colorGhostType;

            case "steel":
                return R.color.colorSteelType;

            case "ice":
                return R.color.colorIceType;

            case "dark":
                return R.color.colorDarkType;

            case "dragon":
                return R.color.colorDragonType;

            default:
                return R.color.Pink;
        }
    }
}
