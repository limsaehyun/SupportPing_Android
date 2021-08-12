package com.example.supportping;

import android.media.session.MediaSession;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {
    @SerializedName("Bearer-Token")
    String Token;

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}
