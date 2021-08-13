package com.example.supportping;

import android.media.session.MediaSession;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {
    @SerializedName("Bearer-Token")
    String Token;

    @SerializedName("User-Id")
    int user_id;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
