package com.example.supportping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ServerResponse serverResponse;

    RecyclerView recyclerView;
    ImageButton ib_add;

    MainAdapter mainAdapter;

    ArrayList<MainData> arrayList;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;

    ImageButton ib_menu;

    DrawerLayout drawerLayout;

    TextView tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        System.out.println("토큰" + MainActivity.token);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawer(GravityCompat.START);

                int id = item.getItemId();

                if(id == R.id.myPage) {
                    startActivity(new Intent(HomeActivity.this, MyPageActivity.class));
                }

                return true;
            }
        });

        ib_menu = (ImageButton) findViewById(R.id.ib_menu);
        ib_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(drawerLayout.isDrawerOpen(Gravity.LEFT)) drawerLayout.closeDrawer(Gravity.LEFT);
                else {
                    drawerLayout.openDrawer(Gravity.LEFT);
                }
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        arrayList = new ArrayList<>();

        mainAdapter = new MainAdapter(arrayList, getApplicationContext());
        recyclerView.setAdapter(mainAdapter);

        ib_add = (ImageButton) findViewById(R.id.ib_add);
        ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, PostActivity.class));
            }
        });


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

            System.out.println("테스트2" + startContent);

            MainData mainData = new MainData(startTitle, startContent, startPlace, startmp, "10");
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

        Call<ServerResponse> call = serverAPI.inquiry();

        call.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                serverResponse = response.body();
                StartSetPost(serverResponse);
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                System.out.println("실패");
            }
        });

    }

}