package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class CBT_Level extends App implements View.OnClickListener{
    static int level = 1, levelUnlock;
    private Button level1, level2, level3, level4, level5;
    private ImageButton lock2, lock3, lock4, lock5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cbt_level);

        setHeader();

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        level4 = findViewById(R.id.level4);
        level5 = findViewById(R.id.level5);

        level1.setOnClickListener(this);
        level2.setOnClickListener(this);
        level3.setOnClickListener(this);
        level4.setOnClickListener(this);
        level5.setOnClickListener(this);

        lock2 = findViewById(R.id.level2_lock);
        lock3 = findViewById(R.id.level3_lock);
        lock4 = findViewById(R.id.level4_lock);
        lock5 = findViewById(R.id.level5_lock);
    }

    @Override
    protected void onStart() {
        readFile();
        unlockLevel();
        super.onStart();
    }

    private void unlockLevel() {
        switch (levelUnlock) {
            case 5:
                lock5.setVisibility(View.INVISIBLE);
                level5.setVisibility(View.VISIBLE);
            case 4:
                lock4.setVisibility(View.INVISIBLE);
                level4.setVisibility(View.VISIBLE);
            case 3:
                lock3.setVisibility(View.INVISIBLE);
                level3.setVisibility(View.VISIBLE);
            case 2:
                lock2.setVisibility(View.INVISIBLE);
                level2.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.level1:
                level = 1;
                break;
            case R.id.level2:
                level = 2;
                break;
            case R.id.level3:
                level = 3;
                break;
            case R.id.level4:
                level = 4;
                break;
            case R.id.level5:
                level = 5;
                break;
        }

        Intent intent = new Intent(CBT_Level.this, CBT_Sublevel.class);
        startActivity(intent);
    }
}