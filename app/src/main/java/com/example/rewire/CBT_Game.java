package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CBT_Game extends App implements View.OnClickListener{
    public static int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt_game);

        setHeader();

        final Button level1 = findViewById(R.id.level1);
        level1.setOnClickListener(this);

        final Button level2 = findViewById(R.id.level2);
        level2.setOnClickListener(this);

        final Button level3 = findViewById(R.id.level3);
        level3.setOnClickListener(this);

        final Button level4 = findViewById(R.id.level4);
        level4.setOnClickListener(this);

        final Button level5 = findViewById(R.id.level5);
        level5.setOnClickListener(this);
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

        Intent intent = new Intent(CBT_Game.this, CBT_Sublevel.class);
        startActivity(intent);
    }
}