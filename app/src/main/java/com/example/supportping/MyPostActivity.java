package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostActivity extends AppCompatActivity {

    ImageButton ib_back;

    TextView tv_myPpmp;
    TextView tv_myTitle;
    TextView tv_myContent;
    TextView tv_myPlace;

    Button btn_edit;
    Button btn_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);

        ib_back = (ImageButton) findViewById(R.id.ib_Back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_myTitle = (TextView) findViewById(R.id.tv_myTitle);
        tv_myContent = (TextView) findViewById(R.id.tv_myContent);
        tv_myPlace = (TextView) findViewById(R.id.tv_myPlace);
        tv_myPpmp = (TextView) findViewById(R.id.tv_myPpmp);

        tv_myTitle.setText(PostData.title[MainAdapter.pos]);
        tv_myContent.setText(PostData.content[MainAdapter.pos]);
        tv_myPlace.setText(PostData.place[MainAdapter.pos]);
        tv_myPpmp.setText(PostData.pp[MainAdapter.pos] + " / " + PostData.mp[MainAdapter.pos]);

        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost();
            }
        });
    }

    private void deletePost() {
        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        Call<ServerRequest> call = serverAPI.deletePost(MainActivity.token, MainAdapter.pos);
        call.enqueue(new Callback<ServerRequest>() {
            @Override
            public void onResponse(Call<ServerRequest> call, Response<ServerRequest> response) { }

            @Override
            public void onFailure(Call<ServerRequest> call, Throwable t) { }
        });
        Toast.makeText(MyPostActivity.this, "게시글 삭제가 완료되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}