package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeActivity extends AppCompatActivity {

    ImageButton ib_back;
    TextView tv_seeTitle;
    TextView tv_seeContent;
    TextView tv_seePlace;
    TextView tv_seeppmp;
    TextView tv_name;
    ImageButton ib_join;
    TextView tv_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

        tv_join = (TextView) findViewById(R.id.tv_Join);

        ib_back = (ImageButton) findViewById(R.id.ib_Back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ib_join = (ImageButton) findViewById(R.id.ib_join);
        ib_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PartyJoin();
            }
        });

        tv_seeTitle = (TextView) findViewById(R.id.tv_seeTitle);
        tv_seeContent = (TextView) findViewById(R.id.tv_seeContent);
        tv_seePlace = (TextView) findViewById(R.id.tv_seePlace);
        tv_seeppmp = (TextView) findViewById(R.id.tv_seeppmp);
        tv_name = (TextView) findViewById(R.id.tv_name);

        tv_seeTitle.setText(PostData.title[MainAdapter.pos]);
        tv_seeContent.setText(PostData.content[MainAdapter.pos]);
        tv_seePlace.setText(PostData.place[MainAdapter.pos]);
        tv_seeppmp.setText(PostData.pp[MainAdapter.pos] + " / " + PostData.mp[MainAdapter.pos]);
        tv_name.setText(PostData.name[MainAdapter.pos]);
    }

    private void PartyJoin() {
        if(PostData.overlap[MainAdapter.pos] == 0) {
            PostData.overlap[MainAdapter.pos] = 1;
            tv_join.setText("탈퇴 하기");
            ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);
            serverAPI.joinPost(MainActivity.token, MainAdapter.pos).enqueue(new Callback<ServerRequest>() {
                @Override
                public void onResponse(Call<ServerRequest> call, Response<ServerRequest> response) {
                }

                @Override
                public void onFailure(Call<ServerRequest> call, Throwable t) {
                }
            });
            Toast.makeText(SeeActivity.this, "성공적으로 파티에 참여하셨습니다!\n새로고침을 하여 변경사항을 저장해주세요.", Toast.LENGTH_SHORT).show();
        }
        else if(PostData.overlap[MainAdapter.pos] == 1) {
            PostData.overlap[MainAdapter.pos] = 0;
            tv_join.setText("참여 하기");
            ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);
            serverAPI.enterDelete(MainActivity.token, MainAdapter.pos).enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call call, Response response) {
                }
                @Override
                public void onFailure(Call call, Throwable t) {
                }
            });
            Toast.makeText(SeeActivity.this,"성공적으로 파티에서 탈퇴 하셨습니다!\n새로고침을 하여 변경사항을 저장해주세요!", Toast.LENGTH_SHORT).show();
        }
    }
}