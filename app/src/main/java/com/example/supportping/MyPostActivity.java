package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

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

        tv_myTitle.setText(PostData.title[MainAdapter.pos]);
        tv_myContent.setText(PostData.content[MainAdapter.pos]);
        tv_myPlace.setText(PostData.place[MainAdapter.pos]);
        tv_myPpmp.setText(PostData.pp[MainAdapter.pos] + " / " + PostData.mp[MainAdapter.pos]);

        
    }
}