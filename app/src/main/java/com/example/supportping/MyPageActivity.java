package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPageActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    MainAdapter mainAdapter;

    ArrayList<MainData> arrayList;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;

    ServerResponse serverResponse;

    ServerRequest serverRequest;

    ImageButton ib_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        ib_back = (ImageButton) findViewById(R.id.ib_back);
        ib_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new MainAdapter(arrayList, getApplicationContext(), "MyPageActivity");
        recyclerView.setAdapter(mainAdapter);
    }

    private void StartSetPost(ServerResponse serverResponse) {
        int totleElements = serverResponse.getBoardInfos().size();
        for(int i = 0; i < totleElements; i++) {
            JsonObject jsonObject = serverResponse.getBoardInfos().get(i);

            String startId = jsonObject.get("id").toString();
            String startTitle = jsonObject.get("title").toString();
            String startContent = jsonObject.get("content").toString();
            String startPlace = jsonObject.get("place").toString();
            String startpp = jsonObject.get("pp").toString();
            String startmp = jsonObject.get("mp").toString();

            MainData mainData = new MainData(startTitle, "나", startPlace, startmp, startId);
            arrayList.add(mainData);

            mainAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();


        arrayList.clear();
        mainAdapter.notifyDataSetChanged();

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);

        Call<ServerResponse> call = serverAPI.seePost(MainActivity.token, MainActivity.user_id);

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                serverResponse = response.body();
                System.out.println("code" + response.code());
                StartSetPost(serverResponse);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println("실패");
            }
        });



    }
}