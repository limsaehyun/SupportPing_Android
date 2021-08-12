package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageButton ib_add;

    ArrayList<MainData> arrayList;
    MainAdapter mainAdapter;
    LinearLayout linearLayout;
    LinearLayoutManager linearLayoutManager;

    ImageButton ib_menu;

    DrawerLayout drawerLayout;

    TextView tv_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

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

    @Override
    protected void onResume() {
        super.onResume();

        MainData mainData = new MainData("안드 오류 고쳐줘", "1315 임세현", "512호 변기 위", "30명", "5억 원");
        arrayList.add(mainData);

        mainAdapter.notifyDataSetChanged();
    }

}