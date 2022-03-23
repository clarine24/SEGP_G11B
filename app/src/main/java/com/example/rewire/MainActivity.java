package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setVisibility(View.INVISIBLE);

        final Button cbt_btn = findViewById(R.id.cbt_btn);
        final Button breathing_btn = findViewById(R.id.breathing_btn);

        cbt_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CBT_Game.class);
            startActivity(intent);
        });

        breathing_btn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BT_prompt.class);
            startActivity(intent);
        });
    }
}