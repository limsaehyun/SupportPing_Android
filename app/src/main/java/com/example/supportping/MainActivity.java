package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Interceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView tv_signUpButton;
    ImageButton ib_next;

    EditText et_putID;
    EditText et_putPW;

    RetrofitClient retrofitClient;
    ServerAPI serverAPI;

    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_putID = (EditText) findViewById(R.id.et_putID);
        et_putPW = (EditText) findViewById(R.id.et_putPW);

        tv_signUpButton = (TextView) findViewById(R.id.tv_signUpButton);
        tv_signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AccountActivity.class));
            }
        });

        ib_next = (ImageButton) findViewById(R.id.ib_next);
        ib_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

    }

    private void Login() {
        String username = et_putID.getText().toString();
        String password = et_putPW.getText().toString();

        hideKeyboard();

        if(username.trim().length() == 0 || password.trim().length() == 0 || username == null || password == null) {
            Toast.makeText(MainActivity.this, "올바른 로그인 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            LoginResponse();
        }
    }

    private void hideKeyboard() // 키보드 숨기기
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_putID.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_putPW.getWindowToken(), 0);
    }

    public void LoginResponse() {
        String username = et_putID.getText().toString().trim();
        String password = et_putPW.getText().toString().trim();

        // 정보 저장
        RequestLogin requestLogin = new RequestLogin(username, password);

        retrofitClient = RetrofitClient.getInstance();
        serverAPI = RetrofitClient.getRetrofitInterface();

        serverAPI.Login(requestLogin).enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                System.out.println("성공");

                if (response.isSuccessful() && response.body() != null) {

                    ResponseLogin result = response.body();

                    if(response.code() == 200) {
                        Toast.makeText(MainActivity.this, username + "님 환영합니다.", Toast.LENGTH_SHORT).show();
                        token = response.body().getToken();

                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                    if(response.code() == 401) {
                        Toast.makeText(MainActivity.this, "로그인에 실패하였습니다.\n(아이디 또는 비밀번호를 다시 확인해주세요)", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                Toast.makeText(MainActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });


    }

}