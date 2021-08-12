package com.example.supportping;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.Call;

public interface ServerAPI {

    @POST("login") // 로그인
    Call<ResponseLogin> Login(@Body RequestLogin requestLogin);

    @POST("join") // 회원가입
    Call<ResponseRegister> Register(@Body RequestRegister requestRegister);

    @GET("board") // 전체 조회
    Call<ServerResponse> inquiry();

    @GET("users/post")
    Call<ServerRequest> createPost(@Body ServerRequest serverRequest);
}
