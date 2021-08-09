package com.example.supportping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private Spinner sp_floor;
    private Spinner sp_location;



    private TextView tv_result1;
    private TextView tv_result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        sp_floor = (Spinner)findViewById(R.id.sp_floor);
        sp_location = (Spinner)findViewById(R.id.sp_location);


        ArrayAdapter<CharSequence> dayadapter = ArrayAdapter.createFromResource(
                this, R.array.floor, android.R.layout.simple_spinner_item);
        dayadapter.setDropDownViewResource(R.layout.activity_post);
        sp_floor.setAdapter(dayadapter);

        tv_result2 = (TextView)findViewById(R.id.tv_result2);

        ArrayAdapter<CharSequence> locationadapter = ArrayAdapter.createFromResource(
                this, R.array.room, android.R.layout.simple_spinner_item);
        locationadapter.setDropDownViewResource(R.layout.activity_post);
        sp_location.setAdapter(locationadapter);

        sp_floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

                List<String> s = Arrays.asList(getResources().getStringArray(R.array.room));

                if (pos == 1) {
                    s = s.subList(0, 1);
                    ArrayAdapter<String> flooradapter = new  ArrayAdapter<String>(PostActivity.this, android.R.layout.simple_spinner_item,s);
                    sp_floor.setAdapter(flooradapter);
                } else if (pos ==  2) {
                    s = s.subList(5, 6);
                    ArrayAdapter<String> flooradapter = new  ArrayAdapter<String>(PostActivity.this, android.R.layout.simple_spinner_item,s);
                    sp_floor.setAdapter(flooradapter);
                } else if (pos ==  3) {
                    s = s.subList(0,28);
                    ArrayAdapter<String> flooradapter = new  ArrayAdapter<String>(PostActivity.this, android.R.layout.simple_spinner_item,s);
                    sp_floor.setAdapter(flooradapter);
                }
                else if (pos ==  4) {
                    s = s.subList(0,28);
                    ArrayAdapter<String> flooradapter = new  ArrayAdapter<String>(PostActivity.this, android.R.layout.simple_spinner_item,s);
                    sp_floor.setAdapter(flooradapter);
                }
                else if (pos ==  5) {
                    s = s.subList(0,28);
                    ArrayAdapter<String> flooradapter = new  ArrayAdapter<String>(PostActivity.this, android.R.layout.simple_spinner_item,s);
                    sp_floor.setAdapter(flooradapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tv_result2.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}