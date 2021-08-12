package com.example.supportping;

public class MainData {

    // 게시물 관련
    String title;
    String nickname;
    String place;
    String mp;
    String id;

    public MainData(String title, String nickname, String place, String mp, String id) {
        this.title = title;
        this.nickname = nickname;
        this.place = place;
        this.mp = mp;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
