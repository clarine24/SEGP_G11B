package com.example.rewire;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class BT_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique_main);

        Header header = findViewById(R.id.header);
        header.initHeader();
        header.home_btn.setOnClickListener(v -> startActivity(header.toHomeMenu()));
    }
}
