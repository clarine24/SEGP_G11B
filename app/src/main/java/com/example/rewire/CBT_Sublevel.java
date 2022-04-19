package com.example.rewire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CBT_Sublevel extends App implements View.OnClickListener {
    public static int scene = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cbt_sublevel);

        setHeader();

        TextView title = findViewById(R.id.title);
        title.setText("LEVEL " + CBT_Game.level);

        final Button scene_1 = findViewById(R.id.scenario1);
        scene_1.setOnClickListener(this);

        final Button scene_2 = findViewById(R.id.scenario2);
        scene_2.setOnClickListener(this);

        final Button scene_3 = findViewById(R.id.scenario3);
        scene_3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.scenario1:
                scene = 1;
                break;
            case R.id.scenario2:
                scene = 2;
                break;
            case R.id.scenario3:
                scene = 3;
                break;
        }

        Intent intent = new Intent(CBT_Sublevel.this, in_game_educationalpage.class);
        startActivity(intent);
    }
}