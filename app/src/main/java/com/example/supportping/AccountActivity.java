package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountActivity extends AppCompatActivity {

    TextView tv_signIn;
    EditText et_RegisterName;
    EditText et_RegisterId;
    EditText et_RegisterPw;
    ImageButton ib_Register;

    RetrofitClient retrofitClient;
    ServerAPI serverAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        et_RegisterName = (EditText) findViewById(R.id.et_RegisterName);
        et_RegisterId = (EditText) findViewById(R.id.et_RegisterId);
        et_RegisterPw = (EditText) findViewById(R.id.et_RegisterPw);

        tv_signIn = (TextView) findViewById(R.id.tv_signIn);
        tv_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ib_Register = (ImageButton) findViewById(R.id.ib_Register);
        ib_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();;
            }
        });
    }

    private void Register() {
        String username = et_RegisterId.getText().toString();
        String password = et_RegisterPw.getText().toString();
        String name = et_RegisterName.getText().toString();

        hideKeyboard();

        if(username.trim().length() == 0 || password.trim().length() == 0  || name.trim().length() == 0 || username == null || password == null || name == null) {
            Toast.makeText(AccountActivity.this, "올바른 정보를 입력해주세요.", Toast.LENGTH_SHORT).show();
        } else {
            RegisterResponse();
        }
    }

    private void hideKeyboard() // 키보드 숨기기
    {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_RegisterPw.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_RegisterName.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_RegisterId.getWindowToken(), 0);
    }

    public void RegisterResponse() {
        String username = et_RegisterId.getText().toString().trim();
        String password = et_RegisterPw.getText().toString().trim();
        String name = et_RegisterName.getText().toString().trim();

        // 정보 저장
        RequestRegister requestRegister = new RequestRegister(username, password, name);

        retrofitClient = RetrofitClient.getInstance();
        serverAPI = RetrofitClient.getRetrofitInterface();

        serverAPI.Register(requestRegister).enqueue(new Callback<ResponseRegister>() {
            @Override
            public void onResponse(Call<ResponseRegister> call, Response<ResponseRegister> response) {

                System.out.println("성공");

                if (response.isSuccessful() && response.body() != null) {

                    ResponseRegister result = response.body();

                    if (response.code() == 200) {
                        String name = response.body().getName();
                        Toast.makeText(AccountActivity.this, name + "님 가입을 환영합니다.\n로그인 후 서비스를 이용해주세요!", Toast.LENGTH_SHORT).show();

                        finish();
                    } else {
                        Toast.makeText(AccountActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseRegister> call, Throwable t) {
                Toast.makeText(AccountActivity.this, "예기치 못한 오류가 발생했습니다.\n고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}