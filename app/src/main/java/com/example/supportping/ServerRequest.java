package com.example.supportping;

import androidx.annotation.NonNull;

public class ServerRequest {
    private String title;
    private String content;
    private String place;
    private String mp;

    public ServerRequest(String title, String content, String place, String mp) {
        this.title = title;
        this.content = content;
        this.place = place;
        this.mp = mp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMp() {
        return mp;
    }

    public void setMp(String mp) {
        this.mp = mp;
    }
}
