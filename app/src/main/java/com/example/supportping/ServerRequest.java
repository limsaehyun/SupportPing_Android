package com.example.supportping;

public class ServerRequest {
    private String title;
    private String content;
    private String NowLocation;
    private String people;

    public ServerRequest(String title, String content, String nowLocation, String people) {
        this.title = title;
        this.content = content;
        NowLocation = nowLocation;
        this.people = people;
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

    public String getNowLocation() {
        return NowLocation;
    }

    public void setNowLocation(String nowLocation) {
        NowLocation = nowLocation;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
