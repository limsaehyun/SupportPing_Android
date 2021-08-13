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
    ImageButton ib_join;

    static int[] status = new int[10000];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see);

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

        tv_seeTitle.setText(PostData.title[MainAdapter.pos]);
        tv_seeContent.setText(PostData.content[MainAdapter.pos]);
        tv_seePlace.setText(PostData.place[MainAdapter.pos]);
        tv_seeppmp.setText(PostData.pp[MainAdapter.pos] + " / " + PostData.mp[MainAdapter.pos]);
    }

    private void PartyJoin() {
        if(status[MainAdapter.pos] == 0) {
            ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);
            serverAPI.joinPost(MainActivity.token, MainAdapter.pos).enqueue(new Callback<ServerRequest>() {
                @Override
                public void onResponse(Call<ServerRequest> call, Response<ServerRequest> response) {
                }

                @Override
                public void onFailure(Call<ServerRequest> call, Throwable t) {
                }
            });
            status[MainAdapter.pos] = 1;
            Toast.makeText(SeeActivity.this, "성공적으로 파티에 참여하셨습니다!", Toast.LENGTH_SHORT).show();
        } else if (status[MainAdapter.pos] == 1){
            Toast.makeText(SeeActivity.this, "중복 참여는 불가능합니다.", Toast.LENGTH_SHORT).show();
        }
    }
}