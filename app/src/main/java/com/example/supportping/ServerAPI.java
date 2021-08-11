package com.example.supportping;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.Call;

public interface ServerAPI {

    @POST("login") //로그인
    Call<RequestLogin> Login(@Body RequestLogin userData);

    @POST("login") //로그인
    Call<RequestLogin> Register(@Body RequestLogin userData);
}
