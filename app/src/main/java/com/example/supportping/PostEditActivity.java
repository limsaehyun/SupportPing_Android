package com.example.supportping;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostEditActivity extends AppCompatActivity {

    private static View PostButton;
    private Spinner floor;
    private EditText location;
    private TextView NowLocation;
    TextView people;
    private Spinner person;
    private Button button;
    private ImageButton back;

    EditText title;
    EditText content;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_edit);

        floor = (Spinner) findViewById(R.id.floor);
        location = (EditText) findViewById(R.id.location);
        NowLocation = (TextView) findViewById(R.id.NowLocation);
        person = (Spinner) findViewById(R.id.person);

        people = (TextView) findViewById(R.id.people);

        button = (Button) findViewById(R.id.button);
        PostButton = (TextView) findViewById(R.id.PostButton);
        back = (ImageButton)findViewById(R.id.back);

        title = (EditText) findViewById(R.id.title);
        content = (EditText) findViewById(R.id.content);

        Toast.makeText(PostEditActivity.this, "수정 모드로 진입하여 데이터를 불러옵니다...", Toast.LENGTH_SHORT).show();
        title.setText(MyPostActivity.editTitle);
        content.setText(MyPostActivity.editContent);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        floor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                NowLocation.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        person.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                people.setText(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (location != null) {
                    NowLocation.setText(NowLocation.getText() + " " + location.getText().toString());
                }
            }
        });

        PostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushPost();
            }
        });


    }

    private void pushPost() {

        String title = this.title.getText().toString();
        String content = this.content.getText().toString();
        String nowLocation = NowLocation.getText().toString();
        String people = this.people.getText().toString();

        PostData.title[MainAdapter.pos] = title;
        PostData.content[MainAdapter.pos] = content;
        PostData.place[MainAdapter.pos] = nowLocation;
        PostData.mp[MainAdapter.pos] = people;


        Toast.makeText(PostEditActivity.this, "수정이 완료 되었습니다!", Toast.LENGTH_SHORT).show();

        ServerAPI serverAPI = ApiProvider.getInstance().create(ServerAPI.class);
        ServerRequest post = new ServerRequest(title, content, nowLocation, people);

        Call<ServerRequest> call = serverAPI.EditPost(MainActivity.token, post, MainAdapter.pos);
        call.enqueue(new Callback<ServerRequest>() {
            @Override
            public void onResponse(Call<ServerRequest> call, Response<ServerRequest> response) {
                finish();
            }

            @Override
            public void onFailure(Call<ServerRequest> call, Throwable t) {
                finish();
            }
        });

    }
}
