package com.example.sheanwoey_yifan.Login;

import android.text.TextUtils;
import android.util.Patterns;

/**
 * Data class that captures user information for logged in users retrieved from LoginRepository
 */
public class LoggedInUser {

    private int userId;
    private String username;
    private String password;

    public LoggedInUser(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValidEmail()
    {
        if(this.username != null && !TextUtils.isEmpty(username) && Patterns.EMAIL_ADDRESS.matcher(username).matches())
        {
            return true;
        }

        return false;
    }

    public boolean isValidPassword()
    {
        if(this.password != null && this.password.length() >= 8)
        {
            return true;
        }

        return false;
    }
}
