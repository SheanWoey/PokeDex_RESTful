package com.example.sheanwoey_yifan.Login.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sheanwoey_yifan.Login.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(Context context, String username, String password) {

        try {
            SharedPreferences pref = context.getSharedPreferences("User", 0); // 0 - for private mode

            if((pref.getString("username", "").equals(username.trim()))&&(pref.getString("password", "").equals(password.trim()))) {

                LoggedInUser user = new LoggedInUser( java.util.UUID.randomUUID().toString(), username);
                return new Result.Success<>(user);
            }
            else {
                return new Result.Error( new Exception("Invalid User"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }

    public Result<LoggedInUser> register(Context context, String username, String password) {

        try {
            SharedPreferences pref = context.getSharedPreferences("User", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();

            editor.putString("username", username);
            editor.putString("password", password);

            editor.apply();
            editor.commit();

            LoggedInUser user = new LoggedInUser( java.util.UUID.randomUUID().toString(), username);
                return new Result.Success<>(user);

        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }
}
