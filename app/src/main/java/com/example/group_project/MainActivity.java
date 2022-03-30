package com.example.group_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView refresh_button;

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