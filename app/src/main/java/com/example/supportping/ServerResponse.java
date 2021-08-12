package com.example.supportping;

import com.google.gson.JsonObject;

import java.util.List;

public class ServerResponse {

    List<JsonObject> boardInfos;

    public List<JsonObject> getBoardInfos() {
        return boardInfos;
    }
}
