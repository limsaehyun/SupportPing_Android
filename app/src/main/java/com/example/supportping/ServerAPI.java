package com.example.supportping;

import java.util.Map;

import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.Call;
import retrofit2.http.Path;

public interface ServerAPI {

    @POST("login") // 로그인
    Call<ResponseLogin> Login(@Body RequestLogin requestLogin);

    @POST("join") // 회원가입
    Call<ResponseRegister> Register(@Body RequestRegister requestRegister);

    @GET("board") // 전체 조회
    Call<ServerResponse> inquiry();

    @POST("user/post") // 게시글 추가
    Call<ServerRequest> createPost(@Header("Authorization") String token, @Body ServerRequest serverRequest);

    @POST("user/enter/{board-id}") // 파티 참여
    Call<ServerRequest> joinPost(@Header("Authorization") String token, @Path("board-id") int board_id);

    @PATCH("user/update/{board-id}") // 게시글 수정
    Call<ServerRequest> EditPost(@Header("Authorization") String token, @Body ServerRequest serverRequest, @Path("board-id") int board_id);

    @GET("user/me/{user-id}") // 게시글 조회
    Call<ServerResponse> seePost(@Header("Authorization") String token, @Path("user-id") int user_id);

    @DELETE("user/delete/{board-id}") // 게시글 삭제
    Call<ServerRequest> deletePost(@Header("Authorization") String token, @Path("board-id") int board_id);

    @DELETE("/user/exit/{board-id}") // 게시글 탈퇴
    Call<ServerResponse> enterDelete(@Header("Authorization") String token, @Path("board-id") int board_id);


}
