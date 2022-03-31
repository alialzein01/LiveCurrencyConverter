package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

<<<<<<< HEAD:frontend/app/src/main/java/com/example/group_project/MainActivity.java
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
=======
public class MainActivity extends AppCompatActivity {
    private ImageView refresh_button;
>>>>>>> 31df20bb282a5f0a5305d54e05a7f641a6278c45:app/src/main/java/com/example/group_project/MainActivity.java

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    String url = "https://10.2.0.0/apiReq.php";
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        refresh_button = findViewById(R.id.refresh_btn);
        refresh_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(getIntent());
            }
        });
    }

    public void nextP(View view) {
        // Use the intent to move from one page to another, here from home page to calculator page
        Intent in = new Intent(MainActivity.this, calcActivity.class);
        startActivity(in);
    }

    public void refresh(View view){

    }
}